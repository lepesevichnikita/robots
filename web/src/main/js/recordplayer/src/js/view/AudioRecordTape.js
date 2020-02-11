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
    this._offsetX = 0;
    this._offsetY = 0;
  }

  get audioRecord() {
    return this._audioRecord;
  }

  ondragstart(event) {
    event.dataTransfer.setData("text/json",
                               this._audioRecord.toJson());
    this._initialX = event.clientX - this._offsetX;
    this._initialY = event.clientY - this._offsetY;
    this._title.textContent = '';
    this._title.rerender();
  }

  ondrag(event) {
    const currentX = event.clientX - this._initialX;
    const currentY = event.clientY - this._initialY;
    this._offsetX = currentX;
    this._offsetY = currentY;
    this.element.style.transform = `translate3d(${currentX}px, ${currentY}px, 0)`;
  }

  ondragend(event) {
    event.preventDefault();
    this._title.textContent = this._audioRecord.name;
    this._title.rerender();
  }

  ondblclick(event) {
    event.preventDefault();
    this._offsetX = 0;
    this._offsetY = 0;
    this.rerender();
  }

  render() {
    this.setEventListeners({
                             dragstart: this.ondragstart.bind(this),
                             drag: this.ondrag.bind(this),
                             dragend: this.ondragend.bind(this),
                             dblclick: this.ondblclick.bind(this)
                           });
    this._title = new Title({text: this._audioRecord.name});
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
  class: 'narrow center max-height narrow inline-block record-tape',
  draggable: true
};

export default AudioRecordTape;