package org.DesignPattProject.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import org.DesignPattProject.model.elements.Pacman;
import org.DesignPattProject.model.elements.Position;
import org.DesignPattProject.model.elements.ghost.*;
import org.DesignPattProject.model.elements.ghost.ghostStates.ChasedState;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import static java.awt.Font.PLAIN;
import static java.awt.Font.TRUETYPE_FONT;

public class LanternaGUI implements GUI {
    private final Screen screen;

    public LanternaGUI(Screen screen) {
        this.screen = screen;
    }

    public LanternaGUI(int width, int height) throws IOException, FontFormatException, URISyntaxException {
        AWTTerminalFontConfiguration fontConfig = loadSquareFont();
        Terminal terminal = createTerminal(width, height, fontConfig);
        this.screen = createScreen(terminal);
        TextGraphics graphics = this.screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#111111"));
        graphics.fillRectangle(new TerminalPosition(1, 1),
                new TerminalSize(width, height), ' ');
    }

    private Screen createScreen(Terminal terminal) throws IOException {
        final Screen screen;
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        return screen;
    }

    private Terminal createTerminal(int width, int height, AWTTerminalFontConfiguration fontConfig)
            throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height + 1);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        return terminalFactory.createTerminal();
    }

    private AWTTerminalFontConfiguration loadSquareFont()
            throws URISyntaxException, FontFormatException, IOException {

        URL resource = getClass().getClassLoader().getResource("fonts/pacman.ttf");
        File fontFile = new File(resource.toURI());

        Font font = Font.createFont(TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(PLAIN, 24);

        return AWTTerminalFontConfiguration.newInstance(loadedFont);
    }

    public ACTION getNextAction() throws IOException {
        KeyStroke keyStroke = screen.pollInput();
        if (keyStroke == null) return ACTION.NONE;

        if (keyStroke.getKeyType() == KeyType.EOF) return ACTION.QUIT;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'q') return ACTION.QUIT;

        if (keyStroke.getKeyType() == KeyType.ArrowUp) return ACTION.UP;
        if (keyStroke.getKeyType() == KeyType.ArrowRight) return ACTION.RIGHT;
        if (keyStroke.getKeyType() == KeyType.ArrowDown) return ACTION.DOWN;
        if (keyStroke.getKeyType() == KeyType.ArrowLeft) return ACTION.LEFT;

        if (keyStroke.getKeyType() == KeyType.Enter) return ACTION.SELECT;

        return ACTION.NONE;
    }

    @Override
    public void drawPacman(Pacman pacman) {
        switch (pacman.getDirection()) {
            case 'r' -> drawCharacter(pacman.getPosition().getX(), pacman.getPosition().getY(), '<', "#FFD700");
            case 'u' -> drawCharacter(pacman.getPosition().getX(), pacman.getPosition().getY(), 'V', "#FFD700");
            case 'd' -> drawCharacter(pacman.getPosition().getX(), pacman.getPosition().getY(), '^', "#FFD700");
            default -> drawCharacter(pacman.getPosition().getX(), pacman.getPosition().getY(), '>', "#FFD700");
        }
    }

    @Override
    public void drawGhost(Ghost ghost) {
        if (ghost instanceof Blinky) {
            drawBlinky((Blinky) ghost);
        } else if (ghost instanceof Inky) {
            drawInky((Inky) ghost);
        } else if (ghost instanceof Pinky) {
            drawPinky((Pinky) ghost);
        } else if (ghost instanceof Clyde) {
            drawClyde((Clyde) ghost);
        }
    }
    @Override
    public void drawWall(Position position) {
        paintSquare(position.getX(), position.getY(), "#3333FF");
    }

    @Override
    public void drawPacDot(Position position) {
        drawCharacter(position.getX(), position.getY(), '.', "#FFFFFF");
    }

    @Override
    public void drawPowerPellet(Position position) {
        drawCharacter(position.getX(), position.getY(), '*', "#FFFFFF");
    }

    @Override
    public void drawBlinky(Blinky blinky) {
        if(blinky.getState().getClass() == ChasedState.class){
            if (blinky.getState().getTimer() % 2 == 0 && blinky.getState().getTimer() > 30)
            drawCharacter(blinky.getPosition().getX(), blinky.getPosition().getY(), '@', "#FFFFFF");
            else drawCharacter(blinky.getPosition().getX(), blinky.getPosition().getY(), '@', "#5259E8");
        }
        else {
            drawCharacter(blinky.getPosition().getX(), blinky.getPosition().getY(), '@', "#FF0000");
        }
    }

    @Override
    public void drawPinky(Pinky pinky) {
        if(pinky.getState().getClass() == ChasedState.class){
            if (pinky.getState().getTimer() % 2 == 0 && pinky.getState().getTimer() > 30)
            drawCharacter(pinky.getPosition().getX(), pinky.getPosition().getY(), '@', "#FFFFFF");
            else drawCharacter(pinky.getPosition().getX(), pinky.getPosition().getY(), '@', "#5259E8");
        } else {
            drawCharacter(pinky.getPosition().getX(), pinky.getPosition().getY(), '@', "#FFB8FF");
        }
    }

    @Override
    public void drawInky(Inky inky) {
        if(inky.getState().getClass() == ChasedState.class){
            if (inky.getState().getTimer() % 2 == 0 && inky.getState().getTimer() > 30)
            drawCharacter(inky.getPosition().getX(), inky.getPosition().getY(), '@', "#FFFFFF");
            else drawCharacter(inky.getPosition().getX(), inky.getPosition().getY(), '@', "#5259E8");

        } else {
            drawCharacter(inky.getPosition().getX(), inky.getPosition().getY(), '@', "#00FFFF");
        }
    }

    @Override
    public void drawClyde(Clyde clyde) {
        if(clyde.getState().getClass() == ChasedState.class){
            if (clyde.getState().getTimer() % 2 == 0 && clyde.getState().getTimer() >30)
            drawCharacter(clyde.getPosition().getX(), clyde.getPosition().getY(), '@', "#FFFFFF");
            else
                drawCharacter(clyde.getPosition().getX(), clyde.getPosition().getY(), '@', "#5259E8");
        } else {
            drawCharacter(clyde.getPosition().getX(), clyde.getPosition().getY(), '@', "#FFB852");
        }
    }

    @Override
    public void drawText(Position position, String text, String color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.putString(position.getX(), position.getY(), text);
    }

    private void drawCharacter(int x, int y, char c, String color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.putString(x, y + 1, "" + c);
    }

    private void paintSquare(int x, int y, String color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setBackgroundColor(TextColor.Factory.fromString(color));
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.putString(x, y + 1, "#");
    }

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }
}
