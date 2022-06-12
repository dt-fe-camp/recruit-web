/**
 * @file request.ts
 * @author afcfzf(9301462@qq.com)
 */

import axios, { AxiosRequestConfig } from 'axios';
import { message } from 'antd';

declare interface RequestConfig extends AxiosRequestConfig {
  showErrorTipWhenCodeNotZero: boolean;
}

export const SERVER_ERROR_MSG = '服务异常，请稍后重试';

export const BASE_URL = '/recruit';

const statusErrMsgDict: Record<string, string> = {
  401: '当前用户无权限，请登录后重试',
  502: SERVER_ERROR_MSG,
  500: SERVER_ERROR_MSG,
};

const requestConfig: RequestConfig = {
  timeout: 6 * 1000,
  timeoutErrorMessage: '请求超时，请稍后重试',
  baseURL: BASE_URL,
  showErrorTipWhenCodeNotZero: true,
};


export const request = axios.create(requestConfig);

request.interceptors.response.use((resp) => {
  const { status, data, config } = resp;

  const errMsg = statusErrMsgDict[status];
  if (errMsg) {
    message.error(errMsg);
    return;
  }

  const { code, message: msg } = data;
  const { showErrorTipWhenCodeNotZero } = config as RequestConfig;

  if (code !== 0 && showErrorTipWhenCodeNotZero) {
    message.error(msg);
  }

  return data;
}, (err) => {
  if (axios.isCancel(err)) {
    throw err;
  }

  const errMsg = err.message || err;
  message.error(`${SERVER_ERROR_MSG}: ${errMsg}`);
  throw err;
});

interface ResponseStruct<D> {
  code: number;
  message: string;
  data: D;
}

export const get = <R>(url: string, config?: AxiosRequestConfig<unknown>):
Promise<ResponseStruct<R>> => request.get<ResponseStruct<R>, ResponseStruct<R>>(url, config);

export const post = <R>(url: string, data: unknown, config?: AxiosRequestConfig<unknown>):
Promise<ResponseStruct<R>> => request.post<ResponseStruct<R>, ResponseStruct<R>>(url, data, config);
