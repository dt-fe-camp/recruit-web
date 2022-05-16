import { Component } from "react";
import { Link } from 'react-router-dom';
import './citySelecter.less';
import { CITY_DATA } from '../../const/cityData.js';

export default class CitySelecter extends Component {
  state = {
    province: '热门城市',
    city: '',
  };

  getProvince = (val) => {
    this.setState({
      province: val,
    });
  };


  render() {
    return (
      <div className="citySelectWrap">
        <div className="back">
          <Link to={`/${this.state.city}`}>
            <span>&lt;</span>
            <span className="marginL">选择城市</span>
          </Link>
        </div>

        <div className="content">
          <ul className="province">
            {
              CITY_DATA.map((item, idx) => (
                <li key={idx} onClick={() => this.getProvince(item.name)}>{item.name}</li>
              ))
            }
          </ul>
          <ul className="city">
            {
              CITY_DATA.find(item => item.name === this.state.province).value.map((item, idx) => (
                <li key={idx} onClick={() => {
                  // this.getCity(item)
                  this.props.history.push(`/${item}`);
                  // window.location.href = `/${item}`;
                }}>
                  {item}
                </li>
              ))
            }
          </ul>
        </div>
      </div>
    )
  }
}

