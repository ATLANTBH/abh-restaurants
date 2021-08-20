const is_chrome = navigator.userAgent.indexOf('Chrome') > -1;
const is_safari = navigator.userAgent.indexOf("Safari") > -1;


export const isSafariBrowser = () => {
  if (is_safari) {
    if (is_chrome)  // Chrome seems to have both Chrome and Safari userAgents
      return false;
    else
      return true;
  }
  return false;
}

export const isChromeBrowser = () => {
  return is_chrome;
}