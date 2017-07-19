import tinify
tinify.key = "lGaaMJaD_YTxtHN-QqUJNrtRY-U-dYqu"
source = tinify.from_file("icon_splash.png")
source.to_file("optimized.png")