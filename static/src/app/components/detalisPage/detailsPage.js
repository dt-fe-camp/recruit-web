/* eslint-disable max-len */

import React from "react";
import { DETAILS_DATA } from '../../const/detailsData.js';
import { Link } from 'react-router-dom';
import './detailsPage.less';

export default class DetailsPage extends React.Component {
  render() {
    return (
      <div className="details-page">
        <Link to='/:city'>
          <div className='back-button'>
            <span className="back-button-symbol">&lt;</span>
            <span>{DETAILS_DATA.position}</span>
          </div>
        </Link>
        <div className="details-page-header">
          <div className="details-page-header-wrap">
            <div className="position">
              <span className="position-name">{DETAILS_DATA.position}</span>
              <span className="position-salary">{DETAILS_DATA.salary}</span>
            </div>
            <div className="company-name">{DETAILS_DATA.companyName}</div>
            <ul className="requires">
              {
                // eslint-disable-next-line max-len
                Object.keys(DETAILS_DATA.requires).map(item => <li key={item} className='requires-list'><img src='#' />{DETAILS_DATA.requires[item]}</li>)
              }
            </ul>
            <ul className="welfare">
              {
                DETAILS_DATA.welfare.map(item => <li className="welfare-list" key={item.id}>{item.value}</li>)
              }
            </ul>
            <Link to='/a/a/a'>
              <div className="address-wrap">
                <div className="address">
                  <img className="address-img" src='#' />
                  <span className="address-name">{DETAILS_DATA.address}</span>
                  <span className="address-symbol">&gt;</span>
                </div>
              </div>
            </Link>
          </div>
        </div>
        <div className="description-wrap">
          <div className="description">
            <div className="description-header">
              <img className="description-header-symbol" src='#' />
              <span className="description-header-name">职位描述</span>
              <span className="description-header-line"></span>
            </div>
            <ul>
              {
                // eslint-disable-next-line max-len
                DETAILS_DATA.description.map(item => <li className="description-list" key={item.id}>{`${item.id + 1}，${item.value}`}</li>)
              }
            </ul>
          </div>
        </div>
        <div className="company-info-wrap">
          <div className="company-info">
            <div className="company-info-header">
              <img className="company-info-img" src='#' />
              <span className="company-info-name">公司信息</span>
              <span className="company-info-line"></span>
            </div>
            <div className="company-info-content">
              <img className="company-info-content-img" src='#' />
              <ul className="company-info-content-ul">
                <li className="company-info-content-ul-name">{DETAILS_DATA.companyInfo.companyName}</li>
                <li className="company-info-content-ul-list">
                  <ul className="company-info-content-ul-list-ul">
                    {
                      // eslint-disable-next-line max-len
                      DETAILS_DATA.companyInfo.companyDetals.map((item, idx) => <li className="company-info-content-ul-list-ul-list" key={idx}>{item}</li>)
                    }
                  </ul>
                </li>
                <li>
                  <ul className="company-info-content-end">
                    <li key='0'>{DETAILS_DATA.companyInfo.position}</li>
                    <li className="company-info-content-end-list" key='1'>{'在招职位'}<span className="company-info-content-end-count">{DETAILS_DATA.companyInfo.count}</span>{'个'}</li>
                  </ul>
                </li>
              </ul>
              <span className="company-info-content-symbol">&gt;</span>
            </div>
          </div>
        </div>
        <div className="warning-info-wrap">
          <div className="warning-info">
            <img className="warning-info-img" src='#' />
            <span className="warning-info-content">任何以担保或任何理由索取财物，扣押证照，均属违法，请你提高警惕！</span>
          </div>
        </div>
        <div className="button-area-wrap">
          <div className="button-area">
            <button className="button-area-share"><img src='#' /></button>
            <button className="button-area-submit">投递简历</button>
          </div>
        </div>
      </div>
    )
  }
}
