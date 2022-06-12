/**
 * @file index.tsx
 * @author afcfzf(9301462@qq.com)
 */

import { LoginBox } from '@/auth/components/LoginBox';
import cls from './style.less';

export const Login = (): JSX.Element => (
    <div className={cls.main}>
      <div className={cls.mainWrapper}>
        <div className={cls.aside}></div>
        <div className={cls.loginBox}>
          <LoginBox />
        </div>
      </div>
    </div>
);
