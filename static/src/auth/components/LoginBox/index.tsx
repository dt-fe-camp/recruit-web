/**
 * @file index.tsx
 * @author afcfzf(9301462@qq.com)
 */

import { post, setAuthToken } from '@/common/api';
import { AUTH_API } from '@/common/api/auth';
import { useRefCallback } from '@rc-hooks/use';
import { Button, Checkbox, Form, Input } from 'antd';

interface LoginData {
  userName: string;
  password: string;
  rememberMe: boolean;
  agreement: boolean;
}

const initialFormValue: LoginData = {
  userName: '',
  password: '',
  rememberMe: true,
  agreement: true,
};

export const LoginBox = (): JSX.Element => {
  const [form] = Form.useForm<LoginData>();

  const onSubmit = useRefCallback(() => {
    form.validateFields().then((value) => {
      console.log('value: ', value);
      const { agreement, ...data } = value;
      post<{ token: string }>(AUTH_API.LOGIN_URL, data).then((res) => {
        const { code, data } = res;
        if (code === 0) {
          const callbackUrl = new URLSearchParams(window.location.search).get('callback');

          if (data.token) {
            setAuthToken(data.token);
          }

          if (callbackUrl) {
            window.location.href = callbackUrl;
            return;
          }

          window.location.href = '/recruit/manager';
        }
      });
    });
  });

  return (
    <Form form={form} initialValues={initialFormValue}>
      <Form.Item
        name="userName"
        rules={[
          { required: true, message: '必须输入用户名' },
          { min: 3, message: '用户名必须大于3位' },
        ]}
      >
        <Input placeholder="用户名" allowClear />
      </Form.Item>
      <Form.Item
        name="password"
        rules={[
          { required: true, message: '必须输入用户名' },
          { min: 3, message: '密码必须大于3位' },
        ]}
      >
        <Input placeholder="密码" type="password" allowClear />
      </Form.Item>
      <Form.Item
        name="rememberMe"
        style={{ marginRight: 16, marginBottom: 8 }}
        valuePropName="checked"
      >
        <Checkbox>
          <div style={{ fontSize: 12 }}>下次自动登录</div>
        </Checkbox>
      </Form.Item>
      <Form.Item>
        <Button
          type="primary"
          style={{ width: '100%' }}
          onClick={onSubmit}
        >
          企业登录
        </Button>
      </Form.Item>
      <Form.Item
        name="agreement"
        valuePropName="checked"
        rules={[
          { required: true, message: '请阅读并勾选协议' },
        ]}
        style={{ marginRight: 16 }}
      >
        <Checkbox>
          <div style={{ fontSize: 12 }}>
            同意<a href="#">《用户服务协议》</a>和<a href="#">《隐私正则》</a>
          </div>
        </Checkbox>
      </Form.Item>
    </Form>
  );
};
