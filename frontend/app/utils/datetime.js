import moment from "moment";

/**
 * Formats a date-time object.
 *
 * @param {*} dateTime Date time.
 * @param {String} formatString Formatting.
 */
export function format(dateTime, formatString) {
  return moment(dateTime).format(formatString);
}

/**
 * Formats a timeString HH:mm to a date object.
 *
 * @param {String} timeString Time string.
 */
export function timeStringToDate(timeString) {
  return moment(timeString, "HH:mm").toDate();
}

/**
 * Formats a time.
 *
 * @param {Number} dateTime Date/time.
 */
export function formatTime(dateTime) {
  return moment(dateTime).format("HH:mm");
}

/**
 * Formats a date.
 *
 * @param {Number} dateTime Date/time.
 */
export function formatDate(dateTime) {
  return moment(dateTime).format("YYYY-MM-DD");
}

/**
 * Converts two strings to date.
 *
 * @param {String} date Date in YYYY-MM-DD format.
 * @param {String} time Time in HH:mm format.
 */
export function toDate(date, time) {
  if (date && time) {
    return moment(date + " " + time, "YYYY-MM-DD HH:mm").toDate();
  }

  return moment(date).toDate();
}
