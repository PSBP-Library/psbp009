# Program Specification Based Programming

This is lesson 009 of a "Program Specification Based Programming" course.

All comments are welcome at luc.duponcheel[at]gmail.com.

## Introduction

In lesson008 we have specified and, for then, trivially implemented the `ParallelComposition` program concept and defined
`parallelFibonacci` using it.

## Content

We are going to implement`ParallelComposition` in terms of type `Reactive` using the `Akka` actor library.

First we refactor the `Program` implementation in terms of `Computation`, function level `Product` and function level
`Sum` to be a `trait` instead of a `given`.

### Refactoring `Program` implementation in terms of `Computation`, function level `Product` and function level `Sum`.

```scala
package psbp.implementation.program

import psbp.specification.computation.{Computation}

import psbp.implementation.function.{Function}

import psbp.implementation.algorithm.{SequentialComposition, ParallelComposition}

import psbp.implementation.dataStructure.{DataStructure}

private[psbp] trait Program[
    C[+_]: Computation,
    &&[+_, +_]: psbp.specification.Product,
    ||[+_, +_]: psbp.specification.Sum
] extends psbp.specification.program.Program[[Z, Y] =>> Z => C[Y], &&, ||]
    with Function[C, &&]
    with SequentialComposition[C]
    with ParallelComposition[C, &&]
    with DataStructure[C, &&, ||]
```

### Updating `Program` implementation in terms of type `Active`, function level `Product` and function level `Sum`.

The implementation of `Program` in terms of type `Active`, function level `Product` and function level `Sum`needs to be
updated accordingly.

```scala
package psbp.implementation.program.active

import psbp.implementation.computation.active.{Active, `=>A`}

import psbp.implementation.computation.active.{activeComputation}

private[psbp] given activeProgram[
    &&[+_, +_]: psbp.specification.Product,
    ||[+_, +_]: psbp.specification.Sum
]: psbp.specification.program.Program[`=>A`, &&, ||]
  with psbp.implementation.program.Program[Active, &&, ||]
  with {}
```

### Implementing `Program` in terms of type `Reactive`, function level `Product` and function level `Sum`.

```scala
package psbp.implementation.program.reactive

import akka.actor.typed.{ActorSystem, ActorRef, Behavior}

import akka.actor.typed.scaladsl.{Behaviors}

import Behaviors.{receive, stopped}

import psbp.implementation.computation.reactive.{Reactive, `=>R`}

import psbp.implementation.computation.reactive.{reactiveComputation}

private[psbp] given reactiveProgram[
    &&[+_, +_]: psbp.specification.Product,
    ||[+_, +_]: psbp.specification.Sum
]: psbp.specification.program.Program[`=>R`, &&, ||]
  with psbp.implementation.program.Program[Reactive, &&, ||]
  with

  private[psbp] override def parallelComposition[Z, Y, X, W](
      `z=>r[x]`: Z `=>R` X,
      `y=>r[w]`: Y `=>R` W
  ): (Z && Y) `=>R` (X && W) = `z&&y` =>
    `x&&w=>u` =>

      lazy val summonedFunctionalProduct = summon[psbp.specification.Product[&&]]

      import summonedFunctionalProduct.{`(z&&y)=>z`, `(z&&y)=>y`, unfoldProduct}

      lazy val z = `(z&&y)=>z`(`z&&y`)

      lazy val y = `(z&&y)=>y`(`z&&y`)

      object LeftActor:

        case object Act

        def apply(reactor: ActorRef[Reactor.React]): Behavior[Act.type] =
          receive { case (_, _) =>
            `z=>r[x]`(z) { reactor ! Reactor.React.Left(_) }
            stopped
          }

      object RightActor:

        case object Act

        def apply(reactor: ActorRef[Reactor.React]): Behavior[Act.type] =
          receive { case (_, _) =>
            `y=>r[w]`(y) { reactor ! Reactor.React.Right(_) }
            stopped
          }

      object Reactor:

        enum React:
          case Left(x: X) extends React
          case Right(w: W) extends React

        def apply(): Behavior[React] =

          def react(`option[x]`: Option[X], `option[w]`: Option[W]): Behavior[React] =
            receive {
              case (_, React.Left(x)) =>
                `option[w]` match {
                  case Some(w) =>
                    `x&&w=>u`(unfoldProduct(_ => x)(_ => w)(()))
                    stopped
                  case None =>
                    react(Some(x), None)
                }
              case (_, React.Right(w)) =>
                `option[x]` match {
                  case Some(x) =>
                    `x&&w=>u`(unfoldProduct(_ => x)(_ => w)(()))
                    stopped
                  case None =>
                    react(None, Some(w))
                }
            }

          react(None, None)

      lazy val leftActor = ActorSystem(LeftActor(reactor), "leftActor")

      lazy val rightActor = ActorSystem(RightActor(reactor), "rightActor")

      lazy val reactor = ActorSystem(Reactor(), "reactor")

      leftActor ! LeftActor.Act

      rightActor ! RightActor.Act

  override def async[Z, Y](`z=>r[y]`: Z `=>R` Y): Z `=>R` Y =
    z =>
      `y=>u` =>

        object Actor:

          case object Act

          def apply(reactor: ActorRef[Reactor.React[Y]]): Behavior[Act.type] =
            receive { case (_, _) =>
              `z=>r[y]`(z) { reactor ! Reactor.React(_) }
              stopped
            }

        object Reactor:

          case class React[Y](y: Y)

          def react(): Behavior[React[Y]] =
            receive { case (_, React(y)) =>
              `y=>u`(y)
              stopped
            }

          def apply(): Behavior[React[Y]] =
            react()

        lazy val actor = ActorSystem(Actor(reactor), "actor")

        lazy val reactor = ActorSystem(Reactor(), "reactor")

        actor ! Actor.Act
```

`reactor` does its work by handling a callack when it has received both the result of `leftActor` doing its work sent by
`leftActor` to it and the result of `rightActor` doing its work sent by `rightActor` to it.

## Conclusion

We have implemented `ParallelComposition` in terms of type `Reactive`, function level `Product`, and function level
`Sum` using the `Akka` actor library.
