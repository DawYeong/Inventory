package gdx.inventory;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Inventory extends ApplicationAdapter implements InputProcessor {

    SpriteBatch batch;
    ShapeRenderer SR;
    BitmapFont font;
    float fInvX, fInvY, fInvW, fInvH, fMouseY, fItemX, fItemY;
    int nHit = 0, nItemHit = 0;
    boolean isItemHit, isInInventory = false, isIn = false;

    @Override
    public void create() {
        batch = new SpriteBatch();
        SR = new ShapeRenderer();
        font = new BitmapFont();
        fInvW = 100;
        fInvH = 30;
        fInvX = 0;
        fInvY = 0;
        fItemX = 250;
        fItemY = 100;
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        fMouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
        InventoryButton();
        Inventory();
        Item();
        ItemDrop();
    }

    public void InventoryButton() {
        if (nHit == 0) {
            SR.begin(ShapeType.Filled);
            SR.setColor(0, 0, 0, 1);
            SR.rect(fInvX, fInvY, fInvW, fInvH);
            SR.end();
            batch.begin();
            batch.setColor(Color.WHITE);
            font.draw(batch, "Inventory", 20, 20);
            batch.end();
        }
    }

    public void Inventory() {
        if (nHit == 1) {
            SR.begin(ShapeType.Filled);
            SR.setColor(Color.GRAY);
            SR.rect(fInvY, fInvX, 200, 250);
            SR.end();
            SR.begin(ShapeType.Filled);
            SR.setColor(Color.RED);
            SR.rect(180, 230, 20, 20);
            SR.end();
            InventoryBoxes();
            batch.begin();
            font.draw(batch, "X", 185, 245);
            batch.end();
        }
    }

    public void InventoryBoxes() {
        SR.begin(ShapeType.Filled);
        SR.setColor(Color.CYAN);
        SR.rect(30, 160, 50, 50);
        SR.end();
    }

    public void Item() {
        if(isInInventory == false){
        SR.begin(ShapeType.Filled);
        SR.setColor(Color.GREEN);
        SR.circle(fItemX, fItemY, 20);
        SR.end();
        }
    }

    public void ItemDrop() {
        if (isItemHit == true && nItemHit == 1) {
            fItemX = Gdx.input.getX();
            fItemY = fMouseY;
        }
        if(nHit == 1 && nItemHit == 1) {
            if(fItemX >= 30
                    && fItemX <= 80
                    && fItemY >= 160
                    && fItemY <= 210){
                fItemX = 55;
                fItemY = 185;
                isIn = true;
            } else {
                isIn =false;
            }
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;

    }

    @Override
    public boolean keyUp(int keycode) {
        return false;

    }

    @Override
    public boolean keyTyped(char character) {
        return false;

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (nHit == 0) {
            if (Gdx.input.getX() >= fInvX
                    && Gdx.input.getX() <= fInvW + fInvX
                    && fMouseY >= fInvY
                    && fMouseY <= fInvY + fInvH) {
                System.out.println("YESSS");
                isInInventory = false;
                nHit = 1;
            }
        } else if (nHit == 1) {
            if (Gdx.input.getX() >= 180
                    && Gdx.input.getX() <= 200
                    && fMouseY >= 230
                    && fMouseY <= 250) {
                if(isIn == true) {
                    isInInventory = true;
                }
                nHit = 0;
            }
        }
        if (nItemHit == 0) {
            if (Gdx.input.getX() >= fItemX - 20
                    && Gdx.input.getX() <= fItemX + 20
                    && fMouseY >= fItemY - 20
                    && fMouseY <= fItemY + 20) {
                isItemHit = true;
                nItemHit = 1;
                System.out.println("Pickup");
            }
        } else if (nItemHit == 1) {
            if (Gdx.input.getX() >= fItemX - 20
                    && Gdx.input.getX() <= fItemX + 20
                    && fMouseY >= fItemY - 20
                    && fMouseY <= fItemY + 20) {
                System.out.println("DROP");
                nItemHit = 0;
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button
    ) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer
    ) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY
    ) {
        return false;
    }

    @Override
    public boolean scrolled(int amount
    ) {
        return false;
    }
}
