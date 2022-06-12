declare namespace StyleLessNamespace {
  export interface IStyleLess {
    aside: string;
    loginBox: string;
    main: string;
    mainWrapper: string;
  }
}

declare const StyleLessModule: StyleLessNamespace.IStyleLess & {
  /** WARNING: Only available when `css-loader` is used without `style-loader` or `mini-css-extract-plugin` */
  locals: StyleLessNamespace.IStyleLess;
};

export = StyleLessModule;
