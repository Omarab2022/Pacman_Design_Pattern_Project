package org.DesignPattProject.model.arena;

import org.DesignPattProject.model.elements.*;
import org.DesignPattProject.model.elements.ghost.*;
import org.DesignPattProject.sound.SoundFX;
import java.util.ArrayList;
import java.util.List;


public class Arena implements IArenaObservable {
    private static final int MAX_MULTIPLIER = 8;
    private static final int BASELINE_GHOST_SCORE = 200;
    private static final int POWER_PELLET_SCORE = 50;
    private static final int PACDOT_SCORE = 10;
    private int multiplier;
    private int height;
    private int width;
    private Pacman pacman;
    private Blinky blinky;
    private Inky inky;
    private Pinky pinky;
    private Clyde clyde;
    private Position hunterSpawn;
    private Position houseSpawn;
    private Position leftPortal;
    private Position rightPortal;

    List<Wall> walls = new ArrayList<Wall>();
    List<PacDot> pacDots = new ArrayList<PacDot>();
    List<PowerPellet> powerPellets = new ArrayList<PowerPellet>();

    public Blinky getBlinky() {
        return this.blinky;
    }

    public Inky getInky() {
        return this.inky;
    }

    public Pinky getPinky() {
        return this.pinky;
    }

    public Clyde getClyde() {
        return this.clyde;
    }

    public void setBlinky(Blinky blinky) {
        this.blinky = blinky;
    }

    public void setInky(Inky inky) {
        this.inky = inky;
    }

    public void setPinky(Pinky pinky) {
        this.pinky = pinky;
    }

    public void setClyde(Clyde clyde) {
        this.clyde = clyde;
    }

    public List<PacDot> getPacDots() {
        return this.pacDots;
    }

    public Pacman getPacman() {
        return this.pacman;
    }

    public void setPacman(Pacman pacman) {
        this.pacman = pacman;
    }



    public Arena() {
        this.multiplier = 1;
        this.width = 0;
        this.height = 0;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void addWall(Wall wall) {
        walls.add(wall);
    }

    public void addPacDot(PacDot pacDot) {
        pacDots.add(pacDot);
    }

    public List<Ghost> getGhosts() {
        List<Ghost> temp = new ArrayList<>();
        temp.add(this.blinky);
        temp.add(this.clyde);
        temp.add(this.inky);
        temp.add(this.pinky);
        return temp;
    }

    public void addPowerPellet(PowerPellet powerPellet) {
        powerPellets.add(powerPellet);
    }

    public int getHeight() {
        return this.height;
    }

    public List<Wall> getWalls() {
        return this.walls;
    }

    public List<PowerPellet> getPowerPellets() {
        return this.powerPellets;
    }

    public int getWidth() {
        return this.width;
    }

    public boolean isEmpty(Position position) {
        for (Wall wall : walls)
            if (wall.getPosition().equals(position))
                return false;
        return true;
    }

    @Override
    public void powerPelletEaten() {
        this.blinky.powerPelletEaten();
        this.inky.powerPelletEaten();
        this.clyde.powerPelletEaten();
        this.pinky.powerPelletEaten();
    }

    public void pacDotRemove(Position position) {
        for (int i = 0; i < this.pacDots.size(); i++) {
            if (pacDots.get(i).getPosition().equals(position)) {
                SoundFX.playEatSound();
                this.pacDots.remove(pacDots.get(i));
                this.getPacman().increaseScore(PACDOT_SCORE);
                break;
            }
        }
    }

    public void powerPelletRemove(Position position) {
        for (int i = 0; i < this.powerPellets.size(); i++) {
            if (powerPellets.get(i).getPosition().equals(position)) {
                SoundFX.powerPelletEatenSound();
                this.powerPellets.remove(powerPellets.get(i));
                powerPelletEaten();
                this.getPacman().increaseScore(POWER_PELLET_SCORE);
                break;
            }
        }
    }

    private void resetMultiplier() {
        if (multiplier == MAX_MULTIPLIER) {
            multiplier = 1;
            return;
        }

        try {
            for (Ghost ghost : this.getGhosts()) {
                if (ghost.getState().isBeingChased()) {
                    return;
                }
            }
        } catch(NullPointerException ignored) {}

        multiplier = 1;
    }

    @Override
    public Ghost isGhost(Position position) {
        try {
            resetMultiplier();
            for (Ghost ghost : this.getGhosts()) {
                if (ghost.getPosition().equals(position)) {
                    if (ghost.getState().isBeingChased()) {
                        this.getPacman().increaseScore(
                                BASELINE_GHOST_SCORE * multiplier);
                        multiplier *= 2;
                    }
                    ghost.pacManCollision();
                    return ghost;
                }
            }
        } catch(NullPointerException ignored) {}

        return null;
    }

    public void setHunterSpawn(Position hunterSpawn){
        this.hunterSpawn = hunterSpawn;
    }

    public Position getHunterSpawn(){
        return this.hunterSpawn;
    }

    public void setHouseSpawn(Position houseSpawn){
        this.houseSpawn = houseSpawn;
    }

    public Position getHouseSpawn(){
        return this.houseSpawn;
    }

    public void setLeftPortal(Position leftPortal){
        this.leftPortal = leftPortal;
    }

    public Position getLeftPortal(){
        return this.leftPortal;
    }

    public void setRightPortal(Position rightPortal){
        this.rightPortal = rightPortal;
    }

    public Position getRightPortal(){
        return this.rightPortal;
    }
}