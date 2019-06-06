import { helper } from "@ember/component/helper";

export function paginationPageNumber(params) {
  return params[0] + 1;
}

export default helper(paginationPageNumber);
