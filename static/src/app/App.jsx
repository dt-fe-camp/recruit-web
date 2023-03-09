import './App.less';

import FindCmps from './components/findCmps/findCmps.js';
import CitySelecter from './components/citySelecter/citySelecter.js';
import { EmptyPage } from './components/emptyPage/emptyPage';
import DetailsPage from './components/detalisPage/detailsPage.js';
import { SelecterPage } from './components/selecterPage/selecterPage.js';
import { BrowserRouter, Switch, Route } from 'react-router-dom';


import { PureComponent } from 'react';
import { calcRem } from './utils/rem';

const updateRem = () => {
  calcRem(document.body.offsetWidth);
};

// v4

export class App extends PureComponent {
  componentDidMount() {
    window.addEventListener('resize', updateRem);
    updateRem();
  }

  componentWillUnmount() {
    window.removeEventListener('resize', updateRem);
  }

  render() {
    return (
      <div className="App">
        <BrowserRouter basename="/recruit/app">
          <Switch>
            <Route exact path="/citySelecter" component={CitySelecter}/>
            <Route exact path="/:city" component={FindCmps}/>
            <Route exact path="/a/emptyPage" component={EmptyPage}/>
            <Route exact path="/a/a/detailsPage" component={DetailsPage}/>
            <Route exact path="/b/b/selecterPage" component={SelecterPage}/>
            <Route component={FindCmps}/>
          </Switch>
        </BrowserRouter>
      </div>
    );
  }
}

// 1. 添加路由 ： A、B、C 几个页面切换: <Link to="/page-a" />（跳转）、<Router><Route path="/page-a" element={<PageA />} /></Router>(注册)
// 2. 写首页 -> router -> 地址选择
// 3. 1个月: 3.8 ~ 4.8
// 4. 前15天，页面完成；后15天，接口完成
