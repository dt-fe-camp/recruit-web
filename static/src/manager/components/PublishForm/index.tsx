/**
 * @file index.tsx
 * @author afcfzf(9301462@qq.com)
 */


import { Form, Input, Modal, Typography, Cascader, Result, Button, Spin, Select, Layout, Col } from 'antd';
import { useCallback, useEffect, useState } from 'react';
import { MANAGE_API, get, post, SERVER_ERROR_MSG } from '@api';
import cls from './index.less';
import { DESC_PLACEHOLDER, PUBLISH_DEFINITION, SHORT_TIP } from './const';
import { ComponentDtsItem, convertToDts, RespDtsItem } from '@/common/utils';
import { useRefCallback } from '@rc-hooks/use';

const { Title } = Typography;

type PublishDtsType = 'education' | 'experience' | 'salary' | 'salary_month' | 'welfare' | 'region' | 'job_type';

type PublishDtsResp = Record<PublishDtsType, {
  dataSource: RespDtsItem[];
}>;

type PublishDts = Record<PublishDtsType, ComponentDtsItem[]>;

enum PageState {
  SUCCESS = 'success',
  LOADING = 'loading',
  FAIL = 'fail'
}


const openPublishDefinition = (): void => {
  Modal.info({
    title: '职位描述规范',
    content: (
      <ul>
        {PUBLISH_DEFINITION.map(item => <li>
          <Title className="definition-title" level={5}>{item.title}</Title>
          <ul className="definition-content">
            {item.children.map((cell, idx) => (
              <li className="definition-content-item">
                {`${idx > 0 ? `${idx}、` : ''}${cell}`}
              </li>
            ))}
          </ul>
        </li>)}
      </ul>
    ),
    width: 600,
  });
};


interface FormValue {
  description: string;
  education: string;
  experience: string;
  maxSalary: string;
  minSalary: string;
  title: string;
  regionCode: string[];
  regionDetail: string;
  salaryMonth: string;
  shortTip: string;
  welfare: string[];
}

// const initialValues: FormValue = {
//   description: '1. 项目技术栈先进\n2. 有技术进步空间\n3. 待遇优厚',
//   education: '7',
//   experience: '4',
//   maxSalary: '11',
//   minSalary: '10',
//   title: `web前端开发-${guid()}`,
//   regionCode: ['533', '577', '577'],
//   regionDetail: '城区御鑫亮城小区',
//   salaryMonth: '12',
//   shortTip: '学习能力强',
//   welfare: ['10001', '10002', '10003', '10005', '10006'],
// };

export const PublishForm = (): JSX.Element => {
  const [dtsMap, setDtsMap] = useState<PublishDts>();
  const [pageState, setPageState] = useState<PageState>(PageState.LOADING);

  const [form] = Form.useForm<FormValue>();

  const onFinish = (values: any) => {
    console.log(values);
  };

  const onSubmit = useRefCallback((): void  => {
    form.validateFields().then((value) => {
      console.log('formValue: ', value);
      setPageState(PageState.LOADING);
      post<never>(MANAGE_API.PUBLISH_JOB, value).then((res) => {
        const { code } = res;
        if (code === 0) {
          Modal.success({
            content: `职位发布成功: ${value.title}`,
          });

          form.resetFields();
        }
      })
        .finally(() => setPageState(PageState.SUCCESS));
    });
  });

  const requestData = useCallback(() => {
    setPageState(PageState.LOADING);
    get<PublishDtsResp>(MANAGE_API.PUBLISH_DTS_LIST)
      .then((res) => {
        const { code, data } = res;
        if (code !== 0) {
          throw new Error();
        }

        const componentDts = Object.entries(data).reduce<PublishDts>(
          (map, [key, value]) => ({ ...map, [key as PublishDtsType]: convertToDts(value.dataSource) }),
          {} as PublishDts,
        );
        setDtsMap(componentDts);
        setPageState(PageState.SUCCESS);
      })
      .catch(() => setPageState(PageState.FAIL));
  }, []);

  useEffect(() => {
    requestData();
  }, []);


  console.log('======= ', dtsMap);

  if (pageState === PageState.FAIL) {
    return (
      <Result
        status="500"
        title="500"
        subTitle={SERVER_ERROR_MSG}
        extra={<Button type="primary" onClick={requestData}>重试</Button>}
      />
    );
  }

  return (
    <Spin spinning={pageState === PageState.LOADING}>
      <header className={cls.title}>填写职位信息，系统将为您匹配合适的人才</header>
      <Form
        labelCol={{ span: 3 }}
        labelAlign="right"
        className={cls.form}
        form={form}
        name="control-hooks"
        onFinish={onFinish}
        // initialValues={initialValues}
      >
        <Form.Item name="title" label="职位名称" rules={[{ required: true, message: '请输入职位名称' }]}>
          <Input />
        </Form.Item>
        <Form.Item label="职位描述" required >
          <div className={cls.formContentDesc}>
            请勿输入邮箱、电话、链接, 请不要包含性别歧视语<a onClick={openPublishDefinition}>请参考职位描述规范</a>
          </div>
          <Form.Item name="description" rules={[{ required: true, message: '请输入职位描述'  }]}>
            <Input.TextArea showCount maxLength={10000} rows={10} placeholder={DESC_PLACEHOLDER} />
          </Form.Item>
        </Form.Item>
        <Form.Item label="职位亮点">
          <div className={cls.formContentDesc}>
            向求职者介绍您的职位亮点，增加独特的吸引力，让您的职位脱颖而出！
          </div>
          <Form.Item name="shortTip">
            <Input.TextArea rows={8} placeholder={SHORT_TIP} />
          </Form.Item>
        </Form.Item>
        <Form.Item
          label="职位类型"
          name="jobType"
          required
          rules={[{ required: true, message: '请选择职位类型' }]}
        >
          <Select
            allowClear
            placeholder="请选择职位类型"
            options={dtsMap?.job_type}
          />
        </Form.Item>
        <Form.Item label="薪资范围" required style={{ marginBottom: 0 }}>
          <div style={{ display: 'flex', alignItems: 'flex-start' }}>
            <Form.Item
              name="minSalary"
              rules={[{ required: true, message: '请选择最低工资' }]}
              style={{ width: 180 }}
            >
              <Select placeholder="最低" options={dtsMap?.salary} />
            </Form.Item>
            <div style={{ display: 'flex', alignItems: 'flex-start' }}>
              <span style={{ padding: 4, color: '#999' }}>~</span>
              <Form.Item
                name="maxSalary"
                rules={[{ required: true, message: '请选择最高工资' }]}
                style={{ width: 180 }}
              >
                <Select placeholder="最高" options={dtsMap?.salary} />
              </Form.Item>
            </div>
            <div style={{ display: 'flex', alignItems: 'flex-start' }}>
              <span style={{ padding: 4, color: '#999' }}>x</span>
              <Form.Item
                name="salaryMonth"
                rules={[{ required: true, message: '请选择薪资月份' }]}
                style={{ width: 180 }}
              >
                <Select
                  placeholder="薪资月份"
                  options={dtsMap?.salary_month}
                />
              </Form.Item>
            </div>
          </div>
        </Form.Item>
        <Form.Item label="学历|经验" required style={{ marginBottom: 0 }}>
          <Form.Item
            name="education"
            rules={[{ required: true, message: '请选择学历' }]}
            style={{ display: 'inline-block', width: 240, marginRight: 20 }}
          >
            <Select placeholder="最低学历" options={dtsMap?.education} />
          </Form.Item>
          <Form.Item
            name="experience"
            rules={[{ required: true, message: '请选择工作经验' }]}
            style={{ display: 'inline-block', width: 240 }}
          >
            <Select placeholder="工作经验" options={dtsMap?.experience} />
          </Form.Item>
        </Form.Item>
        <Form.Item label="工作地址" required style={{ marginBottom: 0 }}>
          <Form.Item
            name="regionCode"
            rules={[{ required: true, message: '请选择所在区域' }]}
            style={{ display: 'inline-block', width: 240, marginRight: 20 }}
          >
            <Cascader placeholder="请选择地区" options={dtsMap?.region} />
          </Form.Item>
          <Form.Item
            name="regionDetail"
            rules={[{ required: true, message: '请填写详细地址' }]}
            style={{ display: 'inline-block', width: 240 }}
          >
            <Input placeholder="请输入详细工作地址" />
          </Form.Item>
        </Form.Item>
        <Form.Item
          label="职位福利"
          name="welfare"
        >
          <Select
            allowClear
            mode="multiple"
            placeholder="请选择职位福利"
            options={dtsMap?.welfare}
          />
        </Form.Item>
        <Layout className={cls.submit}>
          <Col offset={3}>
            <Button type="primary" onClick={onSubmit}>发布职位</Button>
          </Col>
        </Layout>
      </Form>
    </Spin>
  );
};
