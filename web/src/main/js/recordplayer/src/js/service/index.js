/**
 * @module
 */

/**
 * @see{@link RecordPlayer}
 */
export {RecordPlayer} from "./RecordPlayer.js";

export const preventEvent = (e) => {
  e = e || event;
  e.preventDefault();
};

export const preventWindow = (eventName) => {
  window.addEventListener(eventName, preventEvent, false);
};

export const preventDefaultDragoverAndDrop = () => {
  preventWindow("dragover");
  preventWindow("drop");
};
