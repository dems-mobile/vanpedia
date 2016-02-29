package com.demsmobile.vanpedia;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.demsmobile.vanpedia.service.Globals;

import java.util.HashMap;

public class SubcategoryActivity extends MainActivity {

    private static final int NUM_ROWS = 4;
    private static final int NUM_COLS = 3;

    HashMap<String, String> categoryColor = new HashMap<String, String>();
    HashMap<String, String[]> categoryIcons = new HashMap<String, String[]>();
    HashMap<String, String[]> subCategory = new HashMap<String, String[]>();


    Globals g = Globals.getInstance();
    String categoryName = g.getCategoryName().toString();

    ImageButton buttons[][];// = new ImageButton[NUM_ROWS][NUM_COLS];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategory);

        initializeCategories();

        populateSubCategoryButtons();

        TextView subCategoryHeader = (TextView) findViewById(R.id.subCategoryHeader);
        subCategoryHeader.setText(categoryName);
    }

    private void initializeCategories(){
        categoryColor.put("eat","#db3424");
        subCategory.put("eat", new String[] {"a", "b", "c", "d"});

        categoryColor.put("stay","#71d300");
        subCategory.put("stay", new String[]{"a", "b", "c", "d", "e", "f", "g"});

        categoryColor.put("explore", "#0eaee1");
        subCategory.put("explore", new String[] {"a", "b", "c", "d"});
        /*
        categoryEat.put("subCategories",new String[] { "One", "Two", "Three" } );
        categoryStay.put("color","#71d300");
        categoryExplore.put("color","#0eaee1");

        categories.put("eat", categoryEat);
        categories.put("stay", categoryStay);
        categories.put("explore", categoryExplore);*/
    }

    private void populateSubCategoryButtons(){
        TableLayout table = (TableLayout)findViewById(R.id.tableForButtons);


        String subcategoriesNames[] = subCategory.get(categoryName);//[0].toString();

        int buttonConter = 0;

        int numRows = (subcategoriesNames.length / NUM_COLS) + 1 ;

        System.out.println(numRows);

        buttons = new ImageButton[ numRows ][NUM_COLS];

        for(int row=0; row<numRows; row++){
            final int FINAL_ROW = row;

            TableRow tableRow = new TableRow(this);
/*            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.WRAP_CONTENT,
                    TableLayout.LayoutParams.WRAP_CONTENT,
                    1.0f
            ));*/
            table.addView(tableRow);
            //if ( row % 2 == 0 ) {
            for(int col=0; col<NUM_COLS; col++){

                final int FINAL_COL = col;

                if ( buttonConter >= subcategoriesNames.length) {
                    break;
                }



                ImageButton button = new ImageButton(this);


                button.setColorFilter(Color.parseColor("#ffffff"));
                button.setBackgroundResource(R.drawable.round_button);
                button.setImageResource(R.drawable.glyphicons_497_stay);

                Object btnBgColor = categoryColor.get(categoryName);

                button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(btnBgColor.toString())));
                button.setPadding(0, 0, 0, 0);

                button.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gridButtonClicked(FINAL_ROW, FINAL_COL);
                    }
                });
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableLayout.LayoutParams.WRAP_CONTENT,
                        TableLayout.LayoutParams.WRAP_CONTENT,
                        1.0f
                ));
                tableRow.addView(button);
                buttons[FINAL_ROW][FINAL_COL] = button;

                TextView textView = new TextView(this);
                textView.setText(subcategoriesNames[buttonConter++ ].toString());
                textView.setTextColor(Color.parseColor("#ffffff"));
                tableRow.addView(textView);
            }

           // table.addView(tableRow);

           /* for(int col=0; col<NUM_COLS; col++){

                final int FINAL_COL = col;

                TextView textView = new TextView(this);
               // textView.setText(subcategoriesNames[ (FINAL_ROW + 1 ) / 2 + FINAL_COL ]);
                textView.setTextColor(Color.parseColor("#ffffff"));
                tableRow.addView(textView);
            }*/


        }
    }

    private void gridButtonClicked(int row, int col) {
        ImageButton button = buttons[row][col];
        //button.setBackgroundResource(R.drawable.glyphicons_476_eat);
//        String btnName = getResources().getResourceEntryName(v.getId());
       // Toast.makeText(SubcategoryActivity.this, "Clicked " + btnName, Toast.LENGTH_SHORT).show();
    }
}
