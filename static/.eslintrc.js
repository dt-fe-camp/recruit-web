module.exports = {
  root: true,
  parser: '@typescript-eslint/parser',
  parserOptions: {
    babelOptions: {
      presets: ['@babel/preset-react'],
    },
    sourceType: 'module',
    ecmaVersion: 8,
    ecmaFeatures: {
      jsx: true,
      experimentalObjectRestSpread: true,
    },
    tsconfigRootDir: __dirname,
    project: ['./tsconfig.json'],
  },
  plugins: ['react'],
  rules: {
    'no-param-reassign': 'off',
    'no-plusplus': 'off',
    'max-len': [
      'warn',
      {
        code: 120,
      },
    ],
    eqeqeq: [2, 'allow-null'],
    semi: ['warn', 'always'],
    'react/jsx-uses-react': 'error',
    'react/jsx-uses-vars': 'error',
  },
  overrides: [
    {
      files: ['*.ts', '*.tsx'],
      extends: ['eslint-config-tencent/ts'],
    },
    {
      files: ['*.js', '*.jsx'],
      extends: ['eslint-config-tencent'],
    },
  ],
};
