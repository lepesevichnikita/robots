import Component from "./Component.js";

export class AudioFileInput extends Component {
  constructor(props) {
    super({elementName: 'input', ...props});
  }
}

export default AudioFileInput;