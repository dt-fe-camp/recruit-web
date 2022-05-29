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
  plugins: ['react', 'jsx-max-len'],
  rules: {
    'no-param-reassign': 'off',
    'no-plusplus': 'off',
    'max-len': [2, 120, 2],
    "jsx-max-len/jsx-max-len": [
      2,
      {
        "lineMaxLength": 120,
        "tabWidth": 2,
        "maxAttributesPerLine": 1
      }
    ],
    eqeqeq: [2, 'allow-null'],
    semi: ['warn', 'always'],
    'react/jsx-uses-react': 'error',
    'react/jsx-uses-vars': 'error',
    'react/no-array-index-key': 'error',
    "jsx-quotes": ["error", "prefer-double"],
  },
  overrides: [{
      files: ['*.ts', '*.tsx'],
      extends: ['eslint-config-tencent', 'eslint-config-tencent/ts'],
      rules: {
        '@typescript-eslint/consistent-type-assertions': 'off'
      }
    },
    {
      files: ['*.js', '*.jsx'],
      extends: ['eslint-config-tencent'],
    },
  ],
};
