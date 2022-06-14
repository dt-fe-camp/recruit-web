/**
 * @file 基础配置
 */

import path from 'path';
import MiniCssExtractPlugin from 'mini-css-extract-plugin';
import { Configuration, DefinePlugin } from 'webpack';
import HtmlWebpackPlugin from 'html-webpack-plugin';
import mockData from './mockData.json';

export const IS_DEV = process.env.NODE_ENV === 'development';

export const PUBLIC_PATH = '/recruit/';

export const FILE_NAME = '[name]/[name].[chunkhash].js';

export const resolve = (...args: string[]): string => path.resolve(__dirname, ...args);

const titleMap: Record<string, string> = {
  app: `客户端${IS_DEV ? '-dev' : ''}`,
  manage: `管理端${IS_DEV ? '-dev' : ''}`,
  auth: `鉴权${IS_DEV ? '-dev' : ''}`,
};

const babelLoader = {
  loader: 'babel-loader',
  options: {
    cacheDirectory: IS_DEV,
    presets: [
      [
        '@babel/preset-env',
        {
          targets: {
            edge: '17',
            firefox: '60',
            chrome: '67',
            safari: '11.1',
          },
        },
      ],
      [
        '@babel/preset-react',
        {
          runtime: 'automatic',
        },
      ],
    ],
    plugins: [
      '@babel/plugin-transform-runtime',
      'transform-class-properties',
    ],
  },
};

const lessLoader = [
  MiniCssExtractPlugin.loader,
  'css-loader',
  {
    loader: 'less-loader',
    options: {
      lessOptions: {
        javascriptEnabled: true,
      },
    },
  },
];

const entry = {
  app: './src/app/index.js',
  manage: './src/manage/index.tsx',
  auth: './src/auth/index.tsx',
};

const CONFIG: Configuration = {
  entry,

  output: {
    filename: '[name]/[name].[chunkhash].js',
    publicPath: PUBLIC_PATH,
    path: resolve('../../server/src/main/resources/fe-static/'),
  },

  resolve: {
    extensions: ['.ts', '.tsx', '.js', '.jsx', '.json', '.less'],
    alias: {
      '@': resolve('../src/'),
      '@api': resolve('../src/common/api'),
    },
  },

  module: {
    rules: [
      {
        test: /\.jsx?$/,
        use: [
          babelLoader,
        ],
        include: [
          resolve('../src'),
          resolve('../node_modules'),
        ],
      },
      {
        test: /\.tsx?$/,
        use: [
          babelLoader,
          {
            loader: 'ts-loader',
            options: {
              transpileOnly: true,
              configFile: resolve('../tsconfig.json'),
              compilerOptions: {
                target: 'es6',
              },
            },
          },
        ],
        include: [
          resolve('../src'),
          resolve('../node_modules'),
        ],
      },
      {
        test: /\.less$/,
        exclude: [
          /\.global\.less$/,
          resolve('../src/app'),
        ],
        use: [
          MiniCssExtractPlugin.loader,
          {
            loader: '@teamsupercell/typings-for-css-modules-loader',
          },
          {
            loader: 'css-loader',
            options: {
              modules: {
                compileType: 'module',
                localIdentName: '[name]__[local]___[hash:base64:5]',
                exportLocalsConvention: 'camelCaseOnly',
              },
            },
          },
          {
            loader: 'less-loader',
            options: {
              lessOptions: {
                javascriptEnabled: true,
              },
            },
          },
        ],
      },
      {
        test: /\.less$/,
        include: [
          resolve('../src/app'),
        ],
        use: lessLoader,
      },
      {
        test: /\.global\.less$/,
        use: lessLoader,
      },
      {
        test: /\.css$/,
        use: [
          IS_DEV ? 'style-loader' : MiniCssExtractPlugin.loader,
          'css-loader',
        ],
      },
      {
        test: /\.(jpeg|jpg|png|gif|svg)$/,
        use: [
          {
            loader: 'url-loader',
            options: {
              limit: 10000,
              name: 'static/img/[name].[hash:8].[ext]',
            },
          },
        ],
      },
      {
        test: /\.(eot|ttf|woff|woff2)$/,
        use: {
          loader: 'file-loader',
          options: {
            name: 'static/font/[name].[hash:8].[ext]',
          },
        },
      },
    ],
  },

  plugins: [
    new DefinePlugin({
      'process.env.BROWSER': true,
      __DEV__: IS_DEV,
    }),
    new MiniCssExtractPlugin({
      filename: '[name]/[name].[fullhash].css',
      chunkFilename: '[name]/[name].[fullhash].css',
    }),
    ...Object.keys(entry).map(item => new HtmlWebpackPlugin({
      title: titleMap[item] || '',
      mockData: IS_DEV ? JSON.stringify(mockData) : '{}',
      filename: `${item}/index.html`,
      chunks: [item],
      template: path.join(__dirname, 'template.html'),
      inject: true,
    })),
  ],
};

export default CONFIG;
