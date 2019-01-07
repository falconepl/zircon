package org.hexworks.zircon.api.component

import org.hexworks.cobalt.databinding.api.property.Property
import org.hexworks.zircon.api.data.GraphicTile

interface Icon : Component {

    var icon: GraphicTile
    val iconProperty: Property<GraphicTile>
}
