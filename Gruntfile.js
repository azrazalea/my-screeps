module.exports = function(grunt) {

  var creds = grunt.file.readJSON("creds.json");
  grunt.loadNpmTasks('grunt-screeps');

  grunt.initConfig({
    screeps: {
      options: {
        email: creds.email,
        password: creds.password,
        branch: 'default',
        ptr: false
      },
      dist: {
        src: ['dist/*.js']
      }
    }
  });
};
