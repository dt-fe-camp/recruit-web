import React from 'react';
import ReactDOM from 'react-dom';
import './../common/css/reset.less';
import './index.css';
import App  from './App';

ReactDOM.render(
  React.createElement(React.StrictMode, {}, [React.createElement(App)]),
  document.getElementById('root'),
);

