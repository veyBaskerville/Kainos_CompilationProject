package com.example.elective1compilationproject;

import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class _11secondGuidedExercise extends AppCompatActivity {

    ImageView batman, superman, ironman, wolverine, dropHere;
    TextView status, heroName;
    ConstraintLayout constraintLayout;
    Animation animateImage, animateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._11thsecond_guided_exercise);

        // Initialize all UI components
        init();

        // Set drag listener for the drop target
        dropHere.setOnDragListener(new DropTargetListener());
    }

    private void init() {
        // Load animations
        animateText = AnimationUtils.loadAnimation(this, R.anim.my_animation);
        animateImage = AnimationUtils.loadAnimation(this, R.anim.blink);

        // Initialize ImageViews and TextViews
        batman = findViewById(R.id.ivBatman);
        superman = findViewById(R.id.ivSuperman);
        wolverine = findViewById(R.id.ivWolverine);
        ironman = findViewById(R.id.ivIronman);
        dropHere = findViewById(R.id.ivHero);
        status = findViewById(R.id.tvStatus);
        heroName = findViewById(R.id.tvNameOfHero);
        constraintLayout = findViewById(R.id.clDragNDrop);

        // Set tags for identifying each hero
        batman.setTag("batman");
        superman.setTag("superman");
        ironman.setTag("ironman");
        wolverine.setTag("wolverine");

        // Set touch listeners to initiate drag for all heroes
        batman.setOnTouchListener(new DragTouchListener());
        superman.setOnTouchListener(new DragTouchListener());
        ironman.setOnTouchListener(new DragTouchListener());
        wolverine.setOnTouchListener(new DragTouchListener());
    }

    // Touch Listener for Dragging
    private class DragTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData clipData = ClipData.newPlainText("hero", view.getTag().toString());
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

                // Start drag
                view.startDragAndDrop(clipData, shadowBuilder, view, 0);
                return true;
            }
            return false;
        }
    }

    // Drag Listener for the Drop Target
    private class DropTargetListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            final View draggedView = (View) event.getLocalState();
            int action = event.getAction();

            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    status.setText("Drag Started");
                    heroName.setText("Drag and drop your hero here!");
                    dropHere.setImageResource(R.drawable.hero); // Default hero image
                    dropHere.startAnimation(animateImage);
                    return true;

                case DragEvent.ACTION_DRAG_ENTERED:
                    status.setText("Drag Entered");
                    dropHere.startAnimation(animateImage);
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:
                    status.setText("Drag Exited");
                    dropHere.clearAnimation();
                    return true;

                case DragEvent.ACTION_DROP:
                    // Get the tag from the dragged view
                    String heroTag = (String) draggedView.getTag();

                    // Set the corresponding image for the dropped hero
                    int resId = getResources().getIdentifier(heroTag, "drawable", getPackageName());
                    Drawable drawable = getResources().getDrawable(resId);

                    dropHere.setImageDrawable(drawable);
                    dropHere.clearAnimation();
                    heroName.setText("Hero: " + heroTag.toUpperCase());
                    status.setText("Hero Dropped!");
                    draggedView.setVisibility(View.INVISIBLE); // Hide the dragged item
                    return true;

                case DragEvent.ACTION_DRAG_ENDED:
                    status.setText("Drag Ended");
                    draggedView.setVisibility(View.VISIBLE); // Reset visibility in case of failure
                    return true;

                default:
                    return false;
            }
        }
    }
}