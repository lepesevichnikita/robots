import {Component} from "./Component.js";
import {AudioRecord} from "../model";
import {Title} from "./Title.js";

/**
 * Builds HTMLElement for audio record tape
 * @class
 * @property {AudioRecord} audioRecord - data about audio record
 */
export class AudioRecordTape extends Component {

  constructor(props = {}) {
    super(props);
    this._audioRecord = props.audioRecord;
    this.setAttributes(AudioRecordTape.DEFAULT_ATTRIBUTES);
    this.setEventListeners({
                             dragstart: this.ondragstart.bind(this),
                             drag: this.ondrag.bind(this),
                             dragend: this.ondragend.bind(this)
                           });
    this._title = new Title({text: this._audioRecord.name});
    this._offsetX = 0;
    this._offsetY = 0;
  }

  get audioRecord() {
    return this._audioRecord;
  }

  ondragstart(event) {
    this._title.hide();
    event.dataTransfer.setData("text/json",
                               this._audioRecord.toJson());
    this._initialX = event.clientX - this._offsetX;
    this._initialY = event.clientY - this._offsetY;
  }

  ondrag(event) {
    const currentX = event.clientX - this._initialX;
    const currentY = event.clientY - this._initialY;
    this._offsetX = currentX;
    this._offsetY = currentY;
    this.element.style.transform = `translate3d(${currentX}px, ${currentY}px, 0)`;
  }

  ondragend() {
    this._title.show();
  }

  render() {
    super.render();
    const children = [
      this._title,
      new Component({
                      attributes: {
                        class: 'icon cassette-tape center max-width tall'
                      }
                    })
    ];
    children.forEach(child => child.appendToChildren(this.element));
    return this.element;
  }
}

AudioRecordTape.DEFAULT_ATTRIBUTES = {
  class: 'narrow center max-height narrow inline-block',
  draggable: true
};

export default AudioRecordTape;