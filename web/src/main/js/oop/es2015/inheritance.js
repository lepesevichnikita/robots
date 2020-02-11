class Animal {
  constructor(props) {
    this._name = props.name;
  }

  sayMyName() {
    console.log(`My name is ${this._name}`);
  }

  move() {
    console.log('Default move')
  }

}

class Pig extends Animal {
  constructor(props) {
    super(props);
  }

  sayMyName() {
    console.log("i am the pig");
    super.sayMyName();
  }
}

class Bird extends Animal {
  move() {
    console.log('Fly')
  }
}

const firstAnimal = new Bird({name: 'BigBird'});
const secondAnimal = new Pig({name: 'RedPig'});

firstAnimal.sayMyName();
secondAnimal.sayMyName();

firstAnimal.move();
secondAnimal.move();

