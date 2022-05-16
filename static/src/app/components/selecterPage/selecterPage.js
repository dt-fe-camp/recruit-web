import React from 'react';
import { Link } from 'react-router-dom';

import './selecterPage.less';
import {
  SELECT_PAGE_DATA,
  PORT_NAME,
  OTHER,
} from '../../const/selecterPageData.js';

export class SelecterPage extends React.Component {
  clearSelect = () => {
    const handle = document.getElementsByClassName('selecter-page-port-ul-li');
    for (const item of handle) {
      item.className = item.className.replace(' selected', '');
    }
  };

  collectData = () => {
    const data = document.getElementsByClassName('selecter-page-port-ul');
    const result = {};
    let i = 0;
    // eslint-disable-next-line no-restricted-syntax
    for (const i in SELECT_PAGE_DATA) {
      result[i] = [];
    }
    for (const item of data) {
      const keys = Object.keys(result);
      for (const j of item.children) {
        if (j.className.indexOf('selected') !== -1) {
          result[keys[i]].push(j.textContent);
        }
      }
      i++;
    }
    console.log(result);
    return result;
  };

  itemSelected = (event) => {
    // eslint-disable-next-line one-var
    const e = event,
      element = e.target.parentNode,
      elementIf = element.previousSibling;
    if (elementIf.innerHTML.indexOf('单选') !== -1) {
      const handle = element.children;
      let counter = 0;
      for (const item of handle) {
        item.className.indexOf('selected') !== -1 ? ++counter : '';
      }
      if (counter < 1) {
        e.target.className += ' selected';
        // eslint-disable-next-line no-else-return
      } else if (
        counter === 1
        && e.target.className.indexOf('selected') !== -1
      ) {
        e.target.className = e.target.className.replace('selected', '');
      } else {
        alert('此项仅支持单选，请重新选择。');
        for (const item of handle) {
          item.className = item.className.replace('selected', '');
        }
      }
    } else {
      e.target.className.indexOf('selected') === -1
        ? (e.target.className += ' selected')
        : (e.target.className = e.target.className.replace('selected', ''));
    }
  };

  componentDidMount() {
    const handle = document.getElementsByClassName('selecter-page-port-name');
    console.log(handle);
    for (const item of handle) {
      item.innerHTML.indexOf('发布时间') === -1
        ? ''
        : (item.innerHTML += `<span class='single-select'>${OTHER[0]}</span>`);
    }
  }

  render() {
    return (
      <div className="selecter-page-wrap">
        <div className="selecter-page">
          <Link to="/:city/">
            <div className="back-button">
              <span className="back-button-symbol">&lt;</span>
              <span>筛选</span>
            </div>
          </Link>
          <div className="selecter-page-port-wrap">
            {Object.keys(SELECT_PAGE_DATA).map((item, idx) => (
              <div className="selecter-page-port">
                <div className="selecter-page-port-name">{PORT_NAME[idx]}</div>
                <ul className="selecter-page-port-ul">
                  {
                    SELECT_PAGE_DATA[item].map(i => (
                      <li
                        className="selecter-page-port-ul-li"
                        key={i.id}
                        onClick={e => this.itemSelected(e)}
                      >
                        {i.value}
                      </li>
                    ))
                  }
                </ul>
              </div>
            ))}
          </div>
          <div className="button-areas-wrap">
            <div className="button-areas">
              <div className="clear-select" onClick={this.clearSelect}>
                清除条件
              </div>
              <div className="submit" onClick={this.collectData}>
                确认
              </div>
            </div>
          </div>
          <div className="aaa"></div>
        </div>
      </div>
    );
  }
}
