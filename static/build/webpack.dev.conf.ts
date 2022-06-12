/**
 * @file webpack.dev.conf.ts
 * @author afcfzf(9301462@qq.com)
 */

import merge from 'webpack-merge';
import 'webpack-dev-server';
import { SourceMapDevToolPlugin } from 'webpack';
import base, { resolve } from './webpack.base.conf';
import CopyWebpackPlugin from 'copy-webpack-plugin';

// @ts-ignore
import FriendlyErrorsPlugin from '@soda/friendly-errors-webpack-plugin';

const isRdProxy = process.env.NODE_API_PROXY === 'true';

const proxyTable = [
  '/recruit/api/admin',
  '/recruit/api/app',
  '/recruit/api/auth',
].map(uri => ({
  [uri]: {
    target: 'https://jsplayer.cn',
    secure: false,
    changeOrigin: true,
  },
}));

const DEV_CONF = merge(base, {
  devServer: {
    devMiddleware: {
      writeToDisk: true,
    },
    compress: true,
    host: '0.0.0.0',
    port: 8787,
    allowedHosts: 'all',
    historyApiFallback: {
      rewrites: [
        {
          from: /^\/recruit\/app\//,
          to: '/recruit/app',
        },
        {
          from: /^\/recruit\/admin\//,
          to: '/recruit/admin',
        },
        {
          from: /^\/recruit\/auth\//,
          to: '/recruit/auth',
        },
      ],
    },
    proxy: isRdProxy ? proxyTable : undefined,
  },

  mode: 'development',

  devtool: 'cheap-module-source-map',

  plugins: [
    new FriendlyErrorsPlugin({
      compilationSuccessInfo: {
        messages: ['Your application is running here: http://127.0.0.1:8787'],
        notes: [],
      },
      onErrors(_: unknown, errors: string) {
        console.error(errors);
      },
    }),
    new SourceMapDevToolPlugin({
      moduleFilenameTemplate(info: any) {
        return `file://${info.absoluteResourcePath}`;
      },
    }),
    new CopyWebpackPlugin({
      patterns: [
        {
          from: resolve(__dirname, '../dist/'),
          to: resolve(__dirname, '../../server/src/main/resources/fe-static/'),
        },
      ],
    }),
  ],

  watchOptions: {
    aggregateTimeout: 200,
    poll: 1000,
    ignored: [resolve('../dist/**/*')],
  },
});

export default DEV_CONF;
