export const calcRem = (screenWidth = 0) => {
  const rootFontSize = screenWidth / 37.5;
  document.documentElement.setAttribute('style', `font-size: ${rootFontSize}px`);
};
