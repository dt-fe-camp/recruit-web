/**
 * @file index.tsx
 * @author afcfzf(9301462@qq.com)
 */

import 'antd/dist/antd.css';
import '../common/css/reset.less';
import { render } from 'react-dom';
import { Login } from './pages/login/index';
import { BrowserRouter, Switch,  Route } from 'react-router-dom';

const Root = (): JSX.Element => (
   <BrowserRouter basename="/recruit/auth">
     <Switch>
       <Route path="*" component={Login}/>
     </Switch>
   </BrowserRouter>
);

render(<Root />, document.querySelector('#root'));
