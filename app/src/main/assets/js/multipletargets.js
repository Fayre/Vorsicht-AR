var World = {
	loaded: false,

	init: function initFn() {
		this.createOverlays();
	},

	createOverlays: function createOverlaysFn() {

		/*
			First an AR.ImageTracker needs to be created in order to start the recognition engine. It is initialized with a AR.TargetCollectionResource specific to the target collection that should be used. Optional parameters are passed as object in the last argument. In this case a callback function for the onTargetsLoaded trigger is set. Once the tracker loaded all its target images, the function worldLoaded() is called.

			Important: If you replace the tracker file with your own, make sure to change the target name accordingly.
			Use a specific target name to respond only to a certain target or use a wildcard to respond to any or a certain group of targets.

			Adding multiple targets to a target collection is straightforward. Simply follow our Target Management Tool documentation. Each target in the target collection is identified by its target name. By using this target name, it is possible to create an AR.ImageTrackable for every target in the target collection.
		*/
		this.targetCollectionResource = new AR.TargetCollectionResource("assets/tracker_two_targets.wtc", {
		});

		this.tracker = new AR.ImageTracker(this.targetCollectionResource, {
			onTargetsLoaded: this.worldLoaded,
            onError: function(errorMessage) {
            	alert(errorMessage);
            }
		});

		/*
			The next step is to create the augmentation. In this example an image resource is created and passed to the AR.ImageDrawable. A drawable is a visual component that can be connected to an IR target (AR.ImageTrackable) or a geolocated object (AR.GeoObject). The AR.ImageDrawable is initialized by the image and its size. Optional parameters allow for position it relative to the recognized target.
		*/

		// Create overlay for page one
		var video = new AR.VideoDrawable("assets/Greenscreen_Demo.mp4", 1, {
		    translate: {
		        x: -0.2,
		        y: -0.12
		    },
		    isTransparent: true
		});

		// add drawable to marker
		var pageOne = new AR.ImageTrackable(this.tracker, "marker", {
		    drawables: {
		        cam: video
		    },
		    onImageRecognized: function onImageRecognizedFn(){
		        video.resume();
		        this.removeLoadingBar();
		    },
		    onImageLost: function onImageLostFn(){
		        video.pause();
		    },
		    onError: function(errorMessage){
		        alert(errorMessage);
		    }
		});

		/*
			Similar to the first part, the image resource and the AR.ImageDrawable for the second overlay are created.
		*/
		var imgTwo = new AR.ImageResource("assets/imageTwo.png");
		var overlayTwo = new AR.ImageDrawable(imgTwo, 0.5, {
			translate: {
				x: 0.12,
				y: -0.01
			}
		});

		/*
			The AR.ImageTrackable for the second page uses the same tracker but with a different target name and the second overlay.
		*/
		var pageTwo = new AR.ImageTrackable(this.tracker, "saito-marker", {
			drawables: {
				cam: overlayTwo
			},
			onImageRecognized: this.removeLoadingBar,
            onError: function(errorMessage) {
            	alert(errorMessage);
            }
		});
	},

	removeLoadingBar: function() {
		if (!World.loaded) {
			var e = document.getElementById('loadingMessage');
			e.parentElement.removeChild(e);
			World.loaded = true;
		}
	},

    //Once the tracker loaded all its target images, the function worldLoaded() is called.
	worldLoaded: function worldLoadedFn() {
		var cssDivInstructions = " style='display: table-cell;vertical-align: middle; text-align: right; width: 50%; padding-right: 15px;'";
		var cssDivSurfer = " style='display: table-cell;vertical-align: middle; text-align: left; padding-right: 15px; width: 38px'";
		var cssDivBiker = " style='display: table-cell;vertical-align: middle; text-align: left; padding-right: 15px;'";

        document.getElementById("test").innerHTML = "The world is loaded!!";
	}
};

World.init();
