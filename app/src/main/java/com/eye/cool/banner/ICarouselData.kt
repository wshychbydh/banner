package com.eye.cool.banner

/**
 * Created by cool on 2018/4/18.
 */
interface ICarouselData<T> : ICarousel {

  var data: ArrayList<T>

  fun getData(position: Int): T {
    return data[convertPosition(position)]
  }

  override fun getInitItem(): Int {
    val size = getDataSize()
    val half = Int.MAX_VALUE / 2
    return if (size > 0) {
      half - half % size
    } else 0
  }

  override fun getDataSize(): Int {
    return data.size
  }

  fun convertPosition(position: Int): Int {
    val size = getDataSize()
    return if (size > 0) {
      position % size
    } else 0
  }

  override fun getCount(): Int {
    return Integer.MAX_VALUE
  }

  override fun getNextPage(currentPage: Int): Int {
    if (!params.recyclable && !params.reversible) return currentPage
    val size = getDataSize()
    if (params.reversible) {
      if (params.direction == CarouselParams.LEFT) {
        val next = currentPage + 1
        return if (next % size == 0) {
          params.direction = CarouselParams.RIGHT
          currentPage - 1
        } else {
          next
        }
      } else {
        val next = currentPage - 1
        return if (next % size <= 0) {
          params.direction = CarouselParams.LEFT
          currentPage + 1
        } else {
          next
        }
      }
    }
    if (params.recyclable) {
      return if (params.direction == CarouselParams.LEFT) currentPage + 1 else currentPage - 1
    }
    return currentPage
  }
}