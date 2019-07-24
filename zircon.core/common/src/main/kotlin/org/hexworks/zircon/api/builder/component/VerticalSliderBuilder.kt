package org.hexworks.zircon.api.builder.component

import org.hexworks.zircon.api.component.Slider
import org.hexworks.zircon.api.component.base.BaseComponentBuilder
import org.hexworks.zircon.api.component.data.CommonComponentProperties
import org.hexworks.zircon.api.component.data.ComponentMetadata
import org.hexworks.zircon.api.component.renderer.ComponentRenderer
import org.hexworks.zircon.api.component.renderer.impl.DefaultComponentRenderingStrategy
import org.hexworks.zircon.internal.component.impl.DefaultVerticalSlider
import org.hexworks.zircon.internal.component.renderer.DefaultSliderRenderer
import kotlin.jvm.JvmStatic
import kotlin.math.max

@Suppress("UNCHECKED_CAST")
/**
 * Builder for the slider. By default, it creates a slider with a range of 100 and 10 steps.
 */
data class VerticalSliderBuilder(
        private var range: Int = 100,
        private var numberOfSteps: Int = 10,
        private var additionalHeightNeeded: Int = 5,
        override var props: CommonComponentProperties<Slider> = CommonComponentProperties(
                componentRenderer = DefaultSliderRenderer()))
    : BaseComponentBuilder<Slider, VerticalSliderBuilder>() {

    private val WIDTH_OFFSET = 4

    fun withRange(range: Int) = also {
        require(range > 0) { "Range must be greater than 0"}
        this.range = range
    }

    fun withNumberOfSteps(steps: Int) = also {
        require(steps in 1..range) { "Number of steps must be greater 0 and smaller than the range" }
        this.numberOfSteps = steps
        additionalHeightNeeded = range.toString().length + WIDTH_OFFSET
        contentSize = contentSize
                .withHeight(max(steps + additionalHeightNeeded, contentSize.height))
    }

    override fun build(): Slider {
        return DefaultVerticalSlider(
                componentMetadata = ComponentMetadata(
                        size = size,
                        position = position,
                        componentStyleSet = componentStyleSet,
                        tileset = tileset),
                range = range,
                numberOfSteps = numberOfSteps,
                isDecorated = decorationRenderers.isEmpty().not(),
                renderingStrategy = DefaultComponentRenderingStrategy(
                        decorationRenderers = decorationRenderers,
                        componentRenderer = props.componentRenderer as ComponentRenderer<Slider>))
    }

    override fun createCopy() = copy(props = props.copy())

    companion object {

        @JvmStatic
        fun newBuilder() = VerticalSliderBuilder()
    }
}