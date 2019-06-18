import { helper } from "@ember/component/helper";
import { formatTime } from "../utils/datetime";

export function timeFormat(params) {
  return formatTime(params[0]);
}

export default helper(timeFormat);
