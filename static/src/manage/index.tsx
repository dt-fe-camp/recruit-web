/**
 * @file index.tsx
 * @author afcfzf(9301462@qq.com)
 */

// import 'antd/dist/antd.css';
import '../common/css/reset.less';
import { render } from 'react-dom';
import { Publish } from './pages/publish/index';
import { BrowserRouter, Switch,  Route } from 'react-router-dom';

const Main = (): JSX.Element => (
  <BrowserRouter basename="/recruit/manage">
    <Switch>
      <Route component={Publish}/>
    </Switch>
  </BrowserRouter>
);

render(<Main />, document.querySelector('#root'));

