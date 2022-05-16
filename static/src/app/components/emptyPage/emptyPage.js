import React from "react";
import './emptyPage.less';
export class EmptyPage extends React.Component {
  render() {
    return (
      <div className="emptyComponentWrap">
        <span>没有匹配的职位，修改搜索条件再试试</span>
      </div>
    )
  }
}
