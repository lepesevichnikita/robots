import Component from "./Component.js";

export class Title extends Component {
  constructor(props) {
    super(props);
    this.setAttributes(Title.DEFAULT_ATTRIBUTES);
    this._textContent = props.text;
  }

  get textContent() {
    return this._textContent;
  }

  set textContent(value) {
    this._textContent = value;
  }

  render() {
    super.render();
    this.element.textContent = this._textContent;
    return this.element;
  }
}

Title.DEFAULT_ATTRIBUTES = {
  class: 'title max-width'
};

export default Title;