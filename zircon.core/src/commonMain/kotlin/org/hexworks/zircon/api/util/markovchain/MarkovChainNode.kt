package org.hexworks.zircon.api.util.markovchain


import org.hexworks.zircon.internal.behavior.Identifiable
import org.hexworks.zircon.internal.util.markovchain.DefaultMarkovChainNode
import kotlin.random.Random
import kotlin.jvm.JvmStatic

/**
 * Represents a markov chain node.
 */
interface MarkovChainNode<T : Any> : Identifiable {

    /**
     * Calculates and returns the next state of this markov chain.
     */
    fun next(): MarkovChainNode<T>

    /**
     * Returns the satellite data stored in this node
     * or an exception if it is not present.
     */
    fun data(): T

    /**
     * Returns the satellite data stored in this node.
     */
    fun dataOrNull(): T?

    /**
     * Adds a new [MarkovChainNode] and returns `this`.
     */
    fun addNext(probability: Double, nextNode: MarkovChainNode<T>): MarkovChainNode<T>

    /**
     * Adds a new [MarkovChainNode] with the given `data` and returns `this`.
     */
    fun setData(data: T): MarkovChainNode<T>

    companion object {

        @JvmStatic
        fun <T : Any> create(): MarkovChainNode<T> = DefaultMarkovChainNode()

        @JvmStatic
        fun <T : Any> create(data: T): MarkovChainNode<T> = DefaultMarkovChainNode(data)

        @JvmStatic
        fun <T : Any> create(data: T, seed: Long): MarkovChainNode<T> = DefaultMarkovChainNode(data, Random(seed))
    }
}
