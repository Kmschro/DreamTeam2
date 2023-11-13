package Players;

import Builders.FrameBuilder;
import Enemies.Fireball;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Keyboard;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.IntersectableRectangle;
import GameObject.SpriteSheet;
import Level.Player;
import Powerups.FireballPU;
import Utils.Direction;
import Utils.Point;
// import Powerups.FireballPU;

import java.util.HashMap;

// This is the class for the Greg player character
// basically just sets some values for physics and then defines animations
public class Greg extends Player {

        // Cooldown timer for shooting fireballs (in frames)
        private int fireballCooldown = 0;
        private static final int FIREBALL_COOLDOWN_DURATION = 30; // Adjust as needed
        SpriteSheet spriteSheet;
        private boolean hasFireballPowerup;
        FireballPU fireballPU;
        
        
        public Greg(float x, float y) {

                super(new SpriteSheet(ImageLoader.load("Gregv1.9.png"), 24, 24), x, y, "STAND_RIGHT");

                gravity = 7.5f;
                terminalVelocityY = 20f;
                jumpHeight = 14.5f;
                jumpDegrade = .5f;
                walkSpeed = 2.3f;
                momentumYIncrease = 5f;
                momentumXIncrease = 5f;
        }

        public void update() {
                super.update();
                
                // Decrease the fireball cooldown timer if it's greater than zero
                if (fireballCooldown > 0) {
                        fireballCooldown--;
                }

                // Check if the spacebar (SHOOT_KEY) is pressed and if the cooldown has expired
                if (Keyboard.isKeyDown(SHOOT_KEY) && fireballCooldown == 0) {
                        createFireball();
                        // Set the cooldown timer
                        fireballCooldown = FIREBALL_COOLDOWN_DURATION;
                }
                
        }

        private boolean isFacingRight() {
                return (facingDirection == Direction.RIGHT);
        }

        public void draw(GraphicsHandler graphicsHandler) {
                super.draw(graphicsHandler);
                // drawBounds(graphicsHandler, new Color(255, 0, 0, 170));
        }

        @Override
        public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
                return new HashMap<String, Frame[]>() {
                        {
                                put("STAND_RIGHT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(0, 0))
                                                                .withScale(3)
                                                                .withBounds(8, 9, 8, 9)
                                                                .build()
                                });

                                put("STAND_LEFT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(0, 0))
                                                                .withScale(3)
                                                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                                                .withBounds(8, 9, 8, 9)
                                                                .build()
                                });

                                put("WALK_RIGHT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(1, 0), 14)
                                                                .withScale(3)
                                                                .withBounds(8, 9, 8, 9)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(1, 1), 14)
                                                                .withScale(3)
                                                                .withBounds(8, 9, 8, 9)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(1, 2), 14)
                                                                .withScale(3)
                                                                .withBounds(8, 9, 8, 9)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(1, 3), 14)
                                                                .withScale(3)
                                                                .withBounds(8, 9, 8, 9)
                                                                .build()
                                });

                                put("WALK_LEFT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(1, 0), 14)
                                                                .withScale(3)
                                                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                                                .withBounds(8, 9, 8, 9)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(1, 1), 14)
                                                                .withScale(3)
                                                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                                                .withBounds(8, 9, 8, 9)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(1, 2), 14)
                                                                .withScale(3)
                                                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                                                .withBounds(8, 9, 8, 9)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(1, 3), 14)
                                                                .withScale(3)
                                                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                                                .withBounds(8, 9, 8, 9)
                                                                .build()
                                });

                                put("JUMP_RIGHT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(2, 0))
                                                                .withScale(3)
                                                                .withBounds(8, 9, 8, 9)
                                                                .build()
                                });

                                put("JUMP_LEFT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(2, 0))
                                                                .withScale(3)
                                                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                                                .withBounds(8, 9, 8, 9)
                                                                .build()
                                });

                                put("FALL_RIGHT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(3, 0))
                                                                .withScale(3)
                                                                .withBounds(8, 9, 8, 9)
                                                                .build()
                                });

                                put("FALL_LEFT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(3, 0))
                                                                .withScale(3)
                                                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                                                .withBounds(8, 9, 8, 9)
                                                                .build()
                                });

                                put("CROUCH_RIGHT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(4, 0))
                                                                .withScale(3)
                                                                .withBounds(8, 12, 8, 6)
                                                                .build()
                                });

                                put("CROUCH_LEFT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(4, 0))
                                                                .withScale(3)
                                                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                                                .withBounds(8, 12, 8, 6)
                                                                .build()
                                });

                                put("DEATH_RIGHT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(5, 0), 8)
                                                                .withScale(3)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(5, 1), 8)
                                                                .withScale(3)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(5, 2), -1)
                                                                .withScale(3)
                                                                .build()
                                });

                                put("DEATH_LEFT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(5, 0), 8)
                                                                .withScale(3)
                                                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(5, 1), 8)
                                                                .withScale(3)
                                                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(5, 2), -1)
                                                                .withScale(3)
                                                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                                                .build()
                                });

                                put("SWIM_STAND_RIGHT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(6, 0))
                                                                .withScale(3)
                                                                .withBounds(8, 9, 8, 9)
                                                                .build()
                                });

                                put("SWIM_STAND_LEFT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(6, 0))
                                                                .withScale(3)
                                                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                                                .withBounds(8, 9, 8, 9)
                                                                .build()
                                });

                                //FLIPPED ANIMATIONS
                                put("PLAYER_FLIPPED_STANDING_RIGHT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(0,0))
                                                                .withScale(3)
                                                                .withImageEffect(ImageEffect.FLIP_VERTICAL)
                                                                .withBounds(8,9,8,9)
                                                                .build()
                                });
                                put("PLAYER_FLIPPED_STANDING_LEFT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(0,0))
                                                                .withScale(3)
                                                                .withImageEffect(ImageEffect.FLIP_H_AND_V)
                                                                .withBounds(8,9,8,9)
                                                                .build()
                                });
                                put("PLAYER_FLIPPED_WALK_RIGHT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(1, 0), 14)
                                                                .withScale(3)
                                                                .withImageEffect(ImageEffect.FLIP_VERTICAL)
                                                                .withBounds(8, 9, 8, 9)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(1, 1), 14)
                                                                .withScale(3)
                                                                .withImageEffect(ImageEffect.FLIP_VERTICAL)
                                                                .withBounds(8, 9, 8, 9)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(1, 2), 14)
                                                                .withScale(3)
                                                                .withImageEffect(ImageEffect.FLIP_VERTICAL)
                                                                .withBounds(8, 9, 8, 9)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(1, 3), 14)
                                                                .withScale(3)
                                                                .withImageEffect(ImageEffect.FLIP_VERTICAL)
                                                                .withBounds(8, 9, 8, 9)
                                                                .build()
                                });

                                put("PLAYER_FLIPPED_WALK_LEFT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(1, 0), 14)
                                                                .withScale(3)
                                                                .withImageEffect(ImageEffect.FLIP_H_AND_V)
                                                                .withBounds(8, 9, 8, 9)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(1, 1), 14)
                                                                .withScale(3)
                                                                .withImageEffect(ImageEffect.FLIP_H_AND_V)
                                                                .withBounds(8, 9, 8, 9)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(1, 2), 14)
                                                                .withScale(3)
                                                                .withImageEffect(ImageEffect.FLIP_H_AND_V)
                                                                .withBounds(8, 9, 8, 9)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(1, 3), 14)
                                                                .withScale(3)
                                                                .withImageEffect(ImageEffect.FLIP_H_AND_V)
                                                                .withBounds(8, 9, 8, 9)
                                                                .build()
                                });
                                put("PLAYER_FLIPPED_JUMP_RIGHT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(2, 0))
                                                                .withScale(3)
                                                                //.withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                                                .withBounds(8, 9, 8, 9)
                                                                .build()
                                });

                                put("PLAYER_FLIPPED_JUMP_LEFT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(2, 0))
                                                                .withScale(3)
                                                                //.withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                                                .withBounds(8, 9, 8, 9)
                                                                .build()
                                });
                                /* 
                                put("PLAYER_FLIPPED_FALL_RIGHT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(3, 0))
                                                                .withScale(3)
                                                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                                                .withBounds(8, 9, 8, 9)
                                                                .build()
                                });

                                put("PLAYER_FLIPPED_FALL_LEFT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(3, 0))
                                                                .withScale(3)
                                                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                                                .withBounds(8, 9, 8, 9)
                                                                .build()
                                });
                                */
                        }
                };
        }

        private void createFireball() {
                // Check if the player is facing left or right
                float playerX = getX();
                Direction playerFacingDirection = isFacingRight() ? Direction.RIGHT : Direction.LEFT;
        
                int fireballX;
                float movementSpeed;
                hasFireballPowerup = getFBPowerup();
        
                // Check if Greg has the fireball powerup
                if (hasFireballPowerup) {
                        // Use the Fireball powerup sprite sheet
                        //spriteSheet = new SpriteSheet(ImageLoader.load("Gregv1.8.1FBPowerup.png"), 24, 24);
                        movementSpeed = 3.0f; // Set the fireball speed
                        if (playerFacingDirection == Direction.RIGHT) {
                                fireballX = Math.round(playerX) + getWidth() - 10;
                        } else {
                                fireballX = (Math.round(playerX - 21)) - 15;
                                movementSpeed = -movementSpeed;
                        }
                
                        int fireballY = Math.round(getY());
                        PlayerFireball fireball = new PlayerFireball(fireballX-1, fireballY, movementSpeed, 60);
                        map.addEnemy(fireball);
                    } else {
                       
                        
                    }
        
        
            }

            
        }
