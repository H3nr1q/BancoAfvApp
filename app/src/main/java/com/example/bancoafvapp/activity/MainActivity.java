    package com.example.bancoafvapp.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.bancoafvapp.R;
import com.example.bancoafvapp.app.BancoAfvApp;
import com.example.bancoafvapp.fragment.ClientesFragment;
import com.example.bancoafvapp.fragment.ProdutosFragment;
import com.example.bancoafvapp.fragment.SelectDatabaseDialogFragment;
import com.example.bancoafvapp.helper.DatabaseSelector;
import com.example.bancoafvapp.helper.DbHelper;
import com.google.android.material.navigation.NavigationView;

    public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
                                SelectDatabaseDialogFragment.OnDialogButtonClick{

        private Toolbar toolbar;
        private DrawerLayout drawerLayout;
        private NavigationView navigationView;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            DatabaseSelector.getInstance().setDbName(DbHelper.DB_1);

            SelectDatabaseDialogFragment databaseDialogFragment = new SelectDatabaseDialogFragment();
            databaseDialogFragment.setOnDialogButtonClick(this);
            databaseDialogFragment.show(getSupportFragmentManager(), "selectDatabase");
            createClienteFragment();

            bindViews();
        }

        public void bindViews(){

            toolbar =  findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
            drawerLayout.addDrawerListener(toggle);

            navigationView = findViewById(R.id.navView);
            navigationView.setNavigationItemSelectedListener(this);

            toggle.syncState();
        }
/*

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {

            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu, menu);


            return super.onCreateOptionsMenu(menu);
        }
*/

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.nav_item_produto: {
                    ProdutosFragment produtosFragment = new ProdutosFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.mainActivityFrameLayout, produtosFragment)
                            .commit();
                    break;
                }
                case R.id.nav_item_cliente: {

                    ClientesFragment clientesFragment = new ClientesFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.mainActivityFrameLayout, clientesFragment)
                            .commit();
                    break;
                }
                case R.id.nav_item_banco1:{
                    selectDatabse(DbHelper.DB_1);
                    break;
                }
                case R.id.nav_item_banco2:{
                    selectDatabse(DbHelper.DB_2);
                    break;
                }
                case R.id.navo_item_banco3:{
                    selectDatabse(DbHelper.DB_3);
                    break;
                }
                default: {
                    //Toast.makeText(this, "Menu Default", Toast.LENGTH_SHORT).show();
                    break;
                }
            }

            drawerLayout.closeDrawer(GravityCompat.START);

            return true;
        }

        private void selectDatabse(String db) {
            DbHelper.getInstance(DatabaseSelector.getInstance().getDbName()).setNull();
            DatabaseSelector.getInstance().setDbName(db);
            createClienteFragment();
        }

        @Override
        public void onBackPressed() {

            if (drawerLayout.isDrawerOpen(GravityCompat.START)){
                drawerLayout.closeDrawer(GravityCompat.START);
            }else {
                super.onBackPressed();
            }
        }

        public void createClienteFragment(){

            ClientesFragment clientesFragment = new ClientesFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainActivityFrameLayout, clientesFragment)
                    .commit();
        }

        @Override
        public void onButtonClick(String string) {
            createClienteFragment();
        }
    }