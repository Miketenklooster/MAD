using System;
using Android.App;
using Android.Content;
using Android.OS;
using Android.Preferences;
using Android.Runtime;
using Android.Support.Design.Widget;
using Android.Support.V7.App;
using Android.Views;
using Android.Widget;
using Xamarin.Essentials;

namespace clickergame
{
    [Activity(Label = "@string/app_name", Theme = "@style/AppTheme.NoActionBar", MainLauncher = true)]
    public class MainActivity : AppCompatActivity
    {

        int counter = 0;
        int plusclick = 1;
        TextView score;
        TextView multiplier;

        protected override void OnCreate(Bundle savedInstanceState)
        {
            base.OnCreate(savedInstanceState);
            Xamarin.Essentials.Platform.Init(this, savedInstanceState);
            SetContentView(Resource.Layout.activity_main);

            counter = Preferences.Get("highscore", 0);
            score = FindViewById<TextView>(Resource.Id.score);
            score.Text = counter.ToString();
            multiplier = FindViewById<TextView>(Resource.Id.multiplier);
            Button btn       = FindViewById<Button>(Resource.Id.add);
            Button increment = FindViewById<Button>(Resource.Id.increment);

            increment.Click += Increment_Click;
            btn.Click += Btn_Click;
        }

        protected override void OnStop()
        {
            base.OnStop();
            Preferences.Set("highscore", counter);
        }

        protected override void OnPause()
        {
            base.OnPause();
            Preferences.Set("highscore", counter);
        }

        private void Increment_Click(object sender, EventArgs e)
        {
            plusclick = plusclick + 1;
            multiplier.Text = plusclick.ToString();
        }

        private void Btn_Click(object sender, EventArgs e)
        {
            counter = counter + plusclick;
            score.Text = counter.ToString();
        }

	}

}

