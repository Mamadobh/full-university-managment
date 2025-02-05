/** @type {import('tailwindcss').Config} */
module.exports = {
  important: true,

  content: [
    "./src/**/*.{html,ts}",
  ],
  theme: {
    extend: {
      colors: {
        loginBg: "#F8FAFC",
          purple_main: "#4D44B5",
        purple_bg: "#F3F4FF",
        grey_3: "#A098AE",
        text_color: "#303972",
        gray_color: "#C1BBEB",
        yellow_color:"#FCC43E",
        orange_color:"#FB7D5B"
      },
    },
  },

  plugins: [],
}
