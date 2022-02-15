package org.hexworks.zircon.scala.api.screen

import org.hexworks.zircon.scala.api.grid.TileGrid

trait Screen {
  def display(): Unit
}

object Screen {
  def create(tileGrid: TileGrid): Screen =
    ???
}
