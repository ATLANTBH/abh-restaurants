import { helper } from "@ember/component/helper";

export function incrementValue(params) {
  return params[0] + 1;
}

export default helper(incrementValue);
