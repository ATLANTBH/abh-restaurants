import { helper } from "@ember/component/helper";
import { formatDate } from "../utils/datetime";

export function formatDateHelper(params) {
  return formatDate(params[0]);
}

export default helper(formatDateHelper);
