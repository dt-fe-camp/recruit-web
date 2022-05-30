/**
 * @file 基础配置
 */

import path from 'path';
import MiniCssExtractPlugin from 'mini-css-extract-plugin';
import ForkTsCheckerWebpackPlugin from 'fork-ts-checker-webpack-plugin';
import { Configuration, DefinePlugin } from 'webpack';
import HtmlWebpackPlugin from 'html-webpack-plugin';
// import { WebpackManifestPlugin } from 'webpack-manifest-plugin';
// import CopyWebpackPlugin from 'copy-webpack-plugin';

export const IS_DEV = process.env.NODE_ENV === 'development';

export const PUBLIC_PATH = '/recruit/';

export const FILE_NAME = '[name]/[name].[chunkhash].js';

export const resolve = (...args: string[]): string => path.resolve(__dirname, ...args);

const babelLoader = {
  loader: 'babel-loader',
  options: {
    cacheDirectory: IS_DEV,
    presets: [
      ['@babel/preset-env',
        {
          modules: 'commonjs',
          loose: true,
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
  admin: './src/admin/index.tsx',
};

const CONFIG: Configuration = {
  // context: path.resolve(__dirname, '../'),

  entry,

  output: {
    filename: '[name]/[name].[chunkhash].js',
    publicPath: PUBLIC_PATH,
    path: resolve('../dist/node-server/static'),
  },

  resolve: {
    extensions: ['.ts', '.tsx', '.js', '.jsx', '.json', '.less'],
    alias: {
      '@': resolve('../src/'),
      '@api': resolve('../src/common/api'),
    },
    fallback: { url: false },
  },

  module: {
    rules: [
      {
        test: /\.jsx?$/,
        use: [
          'cache-loader',
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
                module: 'commonjs',
                target: 'es5',
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
          resolve('../../node_modules'),
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
          resolve('../../node_modules'),
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
    new ForkTsCheckerWebpackPlugin(),
    new MiniCssExtractPlugin({
      filename: '[name]/[name].[hash].css',
      chunkFilename: '[name]/[name].[hash].css',
    }),
    ...Object.keys(entry).map(item => new HtmlWebpackPlugin({
      title: item,
      filename: `${item}/index.html`,
      chunks: [item],
      template: path.join(__dirname, 'template.html'),
      inject: true,
    })),
    // new WebpackManifestPlugin({}),
    // new CopyWebpackPlugin({
    //   patterns: [
    //     {
    //       from: './node_modules/@ts-live/ts-editor/workers/',
    //       to: 'static/',
    //     },
    //     {
    //       from: resolve(__dirname, '../../public/'),
    //       to: 'static/',
    //     },
    //   ],
    // }),
  ],
};

export default CONFIG;
