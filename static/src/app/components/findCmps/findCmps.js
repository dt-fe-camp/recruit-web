import './findCmps.less';
import { MainContent } from '../mainContents/mainContent';
import React from 'react';
import {  Link } from 'react-router-dom';

export default class FindCmps extends React.Component {
  state = {
    content: '初级Web前端工程师',
    selectedCity: '北京',
  };

  changeCity = (val) => {
    this.setState({
      selectedCity: val,
    });
  };
  componentDidMount() {
    if (this.props.match.params.city) {
      this.setState({ selectedCity: this.props.match.params.city });
    }
  }


  render() {
    return (
      <div>
        <div className='findWrap'>
          <div className='innerWrap'>
            <div className='ib'>
              <Link to='/citySelecter'>
                <span className='city'>{this.state.selectedCity} &gt;</span>
              </Link>
            </div>
            <div className='fgDiv'></div>
            <input className='content' placeholder={this.state.content} />
          </div>
          <div className='rightWrap'>
            <span className='select'>筛选 &gt;</span>
          </div>
        </div>
        <MainContent />
      </div>
    );
  }
}

