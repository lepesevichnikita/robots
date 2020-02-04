class Animal {
  constructor(props) {
    this._name = props.name;
  }

  get name() {
    return this._name;
  }

  set name(newName) {
    this._name = newName;
  }

  move() {
    console.log('Default move')
  }

}

class Pig extends Animal {
  constructor(props) {
    super(props);
  }
}

class Bird extends Animal {
  move() {
    console.log('Fly')
  }
}

