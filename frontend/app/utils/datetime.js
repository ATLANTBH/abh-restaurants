import moment from "moment";

/**
 * Formats a time.
 *
 * @param {Number} dateTime Date/time.
 */
export function formatTime(dateTime) {
  return moment(dateTime).format("HH:mm");
}

/**
 * Converts two strings to date.
 *
 * @param {String} date Date in YYYY-MM-DD format.
 * @param {String} time Time in HH:mm format.
 */
export function toDate(date, time) {
  return moment(date + " " + time, "YYYY-MM-DD HH:mm").toDate();
}
