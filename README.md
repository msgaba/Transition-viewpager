# Zooming-and-infinite-Auto-scrolling-viewpager
## Brief
A sample app showcasing a custom viewpager which zooms the active fragment along with infite auto scrolling.
## Screen recording
https://user-images.githubusercontent.com/71500144/215331460-64449aca-414c-43ba-8098-8ddb20787409.mp4
## Reference 
[medium article](https://medium.com/@ankugaba_1907/zooming-and-infinite-auto-scrolling-viewpager-e1ce0cb2de7)
## Functionality
### ZoomingTransformer
Scaling down the items to minimum of MIN_SCALE of the original size, so that the active fragment appears to be of original size, 
gradually scaling down to minimum of MIN_SCALE of original size when in non active position. 
TranslationX ensures the amount of space to be translated on x-axis while item is being scaled which may vary as per requirement.
```
override fun transformPage(page: View, position: Float) {
        if (position <= 1) { // [ -1,1 ]
            val scaleFactor = MIN_SCALE.coerceAtLeast(1 - abs(position))
            // Scale the page down ( between MIN_SCALE and 1 )
            page.scaleX = scaleFactor
            page.scaleY = scaleFactor
            val offset = position * -(3 * pageMarginPx)
            page.translationX = offset
        }
    }
```
### Auto Scrolling
Changing the currentItem for viewpager with the delay of 2 seconds [can vary as per requirement].
The position check in onPageSelected is made to handle the initial condition i.e. when position=0 [viewpager wont have anything to display on left]
```
binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    if(position > 0) {
                        slideHandler.removeCallbacks(slideRunnable)
                        slideHandler.postDelayed(slideRunnable, 2000)
                    } else currentItem = 1
                }
            })
slideRunnable = Runnable {
   /* auto movement for viewpager */
   binding.viewPager.currentItem += 1
  }
```
### Infinite Looping
As soon as our list reaches to the second last position, we append all the elements of the list at the end of it, making it infinite.
```
override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        /** --------- viewholder call -------- **/
        if (position == mList.size - 2)
            viewPager.post(listRunnable)
    }

private val listRunnable = Runnable {
      mList.addAll(mList)
      notifyDataSetChanged()
  }
```
