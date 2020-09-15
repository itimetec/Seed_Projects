
// Task to compile Sass file into CSS, and minify CSS into build directory
var gulp = require('gulp'); // Require gulp

// Sass dependencies
var sass = require('gulp-sass'); // Compile Sass into CSS
var minifyCSS = require('gulp-minify-css'); // Minify the CSS

// Minification dependencies
var minifyHTML = require('gulp-minify-html'); // Minify HTML
var concat = require('gulp-concat'); // Join all JS files together to save space
var stripDebug = require('gulp-strip-debug'); // Remove debugging stuffs
var uglify = require('gulp-uglify'); // Minify JavaScript
var imagemin = require('gulp-imagemin'); // Minify images

// Other dependencies
var size = require('gulp-size'); // Get the size of the project
var browserSync = require('browser-sync'); // Reload the browser on file changes
var jshint = require('gulp-jshint'); // Debug JS files
var stylish = require('jshint-stylish'); // More stylish debugging

// Tasks -------------------------------------------------------------------- >

// Task to compile Sass file into CSS, and minify CSS into build directory
gulp.task('styles', function() {
  gulp.src(['node_modules/bootstrap/scss/bootstrap.scss', 'src/scss/*.scss'])
    .pipe(sass().on('error', sass.logError))
    .pipe(gulp.dest('./src/css'))
    .pipe(minifyCSS())
    .pipe(gulp.dest('./build/styles/'))
    .pipe(browserSync.reload({
      stream: true,
    }));
});

// Task to minify new or changed HTML pages
gulp.task('html', function() {
  gulp.src('./src/*.html')
    .pipe(minifyHTML())
    .pipe(gulp.dest('./build/'));
});

// Task to concat, strip debugging and minify JS files
gulp.task('scripts', function() {
  gulp.src(['./src/scripts/lib.js', './src/scripts/*.js'])
    .pipe(concat('script.js'))
    .pipe(stripDebug())
    .pipe(uglify())
    .pipe(gulp.dest('./build/scripts/'));
});

// Task to minify images into build
gulp.task('images', function() {
  gulp.src('./src/images/*')
  .pipe(imagemin({
    progressive: true,
  }))
  .pipe(gulp.dest('./build/images'));
});

// Task to run JS hint
gulp.task('jshint', function() {
  gulp.src('./src/js/*.js')
    .pipe(jshint())
    .pipe(jshint.reporter('jshint-stylish'));
});

// Task to get the size of the app project
gulp.task('size', function() {
  gulp.src('./src/**')
	.pipe(size({
    showFiles: true,
  }));
});

// Task to get the size of the build project
gulp.task('build-size', function() {
  gulp.src('./build/**')
  .pipe(size({
    showFiles: true,
  }));
});

// Serve application
gulp.task('serve', ['styles', 'html', 'scripts', 'images', 'jshint', 'size'], function() {
  browserSync.init({
    server: {
      baseDir: 'src',
    },
  });
});

// Run all Gulp tasks and serve application
gulp.task('default', ['serve', 'styles'], function() {
  gulp.watch('src/scss/**/*.scss', ['styles']);
  gulp.watch('src/*.html', browserSync.reload);
  gulp.watch('src/js/**/*.js', browserSync.reload);
});