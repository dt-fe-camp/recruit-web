import './mainContent.less';
import { Component } from 'react';
import { Link } from 'react-router-dom';

import { FAKE_DATA } from '../../const/mainContentFakeData.js';

export class MainContent extends Component {
  render() {
    return (
      <div className="mainComponent">
        {FAKE_DATA.map((item, idx) => (
          <Link to="#">
            <ul className="mainList">
              <li key={idx}>
                <div className="wrap">
                  {/* <div className='wrapTop'></div> */}
                  <div className="title">
                    <span className="position">{item.position}</span>
                    <span className="salary">{item.salary}</span>
                  </div>
                  <div className="requirements">
                    <span className="requirementsSpan">{`“${item.requirements}”`}</span>
                  </div>
                  <div className="tag">
                    <ul className="tagList">
                      {item.tag.map((item, idx) => (
                        <li key={idx} className="tagLi">
                          {item}
                        </li>
                      ))}
                    </ul>
                  </div>
                  <div className="company">
                    <div className="icon">
                      <img className="img" src={item.icon} alt="icon" />
                    </div>
                    <span className="companyName">{item.companyName}</span>
                  </div>
                  <ul className="label">
                    {item.label.map((item, idx) => (
                      <li key={idx} className="labelLi">
                        {item}
                      </li>
                    ))}
                  </ul>
                </div>
              </li>
            </ul>
          </Link>
        ))}
      </div>
    );
  }
}
