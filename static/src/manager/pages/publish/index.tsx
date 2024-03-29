/**
 * @file index.tsx
 * @author afcfzf(9301462@qq.com)
 */

import cls from './index.less';
import { PublishForm } from '@/manager/components/PublishForm';

export const Publish = (): JSX.Element => (
    <div className={cls.publish}>
      <div className={cls.publishContent}>
        <PublishForm />
      </div>
    </div>
);
