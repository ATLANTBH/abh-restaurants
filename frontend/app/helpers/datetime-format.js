import { helper } from "@ember/component/helper";
import { format } from "../utils/datetime";

export function formatHelper(params) {
  return format(params[0], params[1]);
}

export default helper(formatHelper);
